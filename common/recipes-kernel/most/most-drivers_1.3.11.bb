DESCRIPTION = "MOST network drivers"
SECTION = "kernel/modules"
PRIORITY = "optional"
LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=84b40460f8374ccbde4c353cc301f5fc"
DEPENDS = "virtual/kernel"

#PN = "most-drivers"
#mostcore version
PR="r7"

SRC_URI = "\
    file://most-drivers.tar.gz \
    file://init \
"

S = "${WORKDIR}"

inherit module update-rc.d

EXTRA_OEMAKE = "KLIB_BUILD=${STAGING_KERNEL_DIR} KLIB=${D}"

do_compile() {
    cd ${S}/mostcore
    oe_runmake KERNEL_SOURCE=${STAGING_KERNEL_DIR}
    cd ${S}/hdm-dim2
    oe_runmake KERNEL_SOURCE=${STAGING_KERNEL_DIR}
    cd ${S}/aim-cdev
    oe_runmake KERNEL_SOURCE=${STAGING_KERNEL_DIR}
    cd ${S}/aim-sound
    oe_runmake KERNEL_SOURCE=${STAGING_KERNEL_DIR}
    cd ${S}/aim-network
    oe_runmake KERNEL_SOURCE=${STAGING_KERNEL_DIR}
    cd ${S}/aim-v4l2
    oe_runmake KERNEL_SOURCE=${STAGING_KERNEL_DIR}

}

INITSCRIPT_NAME = "most"
INITSCRIPT_PARAMS = "start 8 5 2 . stop 21 0 1 6 ."

do_install() {
    # Create shared folder
    install -d ${D}/lib/modules/${KERNEL_VERSION}/most/

    # Copy kernel module
    install -m 0644 ${S}/mostcore/mostcore.ko ${D}/lib/modules/${KERNEL_VERSION}/most/
    install -m 0644 ${S}/hdm-dim2/dim2hdm.ko ${D}/lib/modules/${KERNEL_VERSION}/most/
    install -m 0644 ${S}/hdm-dim2/dim2h2.ko ${D}/lib/modules/${KERNEL_VERSION}/most/
    install -m 0644 ${S}/aim-cdev/aim_cdev.ko ${D}/lib/modules/${KERNEL_VERSION}/most/
    install -m 0644 ${S}/aim-sound/aim_sound.ko ${D}/lib/modules/${KERNEL_VERSION}/most/
    install -m 0644 ${S}/aim-network/aim_network.ko ${D}/lib/modules/${KERNEL_VERSION}/most/
    install -m 0644 ${S}/aim-v4l2/aim_v4l2.ko ${D}/lib/modules/${KERNEL_VERSION}/most/

    # Install init script
    install -d ${D}/${sysconfdir}/init.d
    install -m755 ${WORKDIR}/init ${D}/${sysconfdir}/init.d/most

    # Blacklist dim2hdm to prevent modules autoload
    # /etc/init.d/most will do the work with the proper parameters
    install -d ${D}/${sysconfdir}/modprobe.d
    echo "blacklist dim2hdm" > ${D}/${sysconfdir}/modprobe.d/dim2hdm.conf
}

FILES_${PN}_core = " \
    /lib/modules/${KERNEL_VERSION}/most/dim2hdm.ko \
    /lib/modules/${KERNEL_VERSION}/most/mostcore.ko \
    /lib/modules/${KERNEL_VERSION}/most/aim_cdev.ko \
"

FILES_${PN}_sound = " \
    /lib/modules/${KERNEL_VERSION}/most/aim_sound.ko \
"

FILES_${PN}_network = " \
    /lib/modules/${KERNEL_VERSION}/most/aim_network.ko \
"

FILES_${PN}_v4l2 = " \
    /lib/modules/${KERNEL_VERSION}/most/aim_v4l2.ko \
"

PACKAGES_append = " ${PN}-initd"
FILES_${PN}-initd = " \
    ${sysconfdir}/init.d/most \
    ${sysconfdir}/modprobe.d/dim2hdm.conf \
"
