SUMMARY = "Linux LIN bus drivers and tools"
SECTION = "kernel/modules"
DEPENDS = "virtual/kernel"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://sllin.c;md5=ac3527fbbdb552a45de22cc3f56a7fe4"

PN = "sllin"
PE = "1"
PV = "0.1"

SRC_URI = "git://rtime.felk.cvut.cz/linux-lin.git \
	file://0001-cross-compile.patch;striplevel=2 \
	file://0002-fix-termios-struct-access.patch;striplevel=2 \
	file://0003-disable-debug-fix-otput-formating.patch;striplevel=2 \
"
SRCREV = "5c02d1856f32f8206f467b11cb61faf557c1882f"

S = "${WORKDIR}/git/sllin"

inherit module

EXTRA_OEMAKE = "KPATH=${STAGING_KERNEL_DIR} KLIB=${D}"

do_install() {
    # Create shared folder
    install -d ${D}/lib/modules/${KERNEL_VERSION}/tty/

    # Copy kernel module
    install -m 0644 ${S}/sllin.ko ${D}/lib/modules/${KERNEL_VERSION}/tty/
}

FILES_kernel-module-${PN} = " \
    /lib/modules/${KERNEL_VERSION}/tty/sllin.ko \
"