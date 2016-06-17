DESCRIPTION = "Backported wireless drivers"
HOMEPAGE = "http://backports.wiki.kernel.org/"
SECTION = "kernel/modules"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"
DEPENDS = "wireless-tools"
PR = "r11"

TAG = "${@'${COMPAT_WIRELESS_VERSION}'.replace('-', '.')}"
PV = "${TAG}"

S = "${WORKDIR}/backports-${COMPAT_WIRELESS_VERSION}"

inherit module

EXTRA_OEMAKE = "KLIB_BUILD=${STAGING_KERNEL_DIR} KLIB=${D}"

do_configure_append() {
    sed -i "s#@./scripts/update-initramfs## " Makefile
}

do_install() {
    oe_runmake DEPMOD=echo DESTDIR="${D}" INSTALL_MOD_PATH="${D}" LDFLAGS="" modules_install
}