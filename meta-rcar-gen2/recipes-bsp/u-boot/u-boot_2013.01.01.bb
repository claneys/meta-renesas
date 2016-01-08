require u-boot.inc

# This is needs to be validated among supported BSP's before we can
# make it default
DEFAULT_PREFERENCE = "-1"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=1707d6db1d42237583f50183a5651ecb"

PV = "v2013.01.01+git${SRCPV}"


COMPATIBLE_MACHINE = "(alt|gose|koelsch|lager|porter|silk)"

SRCREV = "7b8ebcc25719f85ae4bce2b6b997baf8070e6d35"
SRC_URI = "git://git.denx.de/u-boot-sh.git;branch=renesas/bsp/rcar-gen2-1.9.6;protocol=git"

S = "${WORKDIR}/git"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

# Some workaround for gcc5 support (tbc):
SRC_URI_append = " \
file://0001-fixup-build-with-gcc5.patch \
file://0001-inline-use-the-gcc-inline-version-instead-of-the-c99.patch \
"

SRC_URI_append_porter = " \
file://0001-uboot-Silk-board-support.patch \
file://0004-uboot-porter-board-support.patch \
file://0005-uboot-serial-sh-SCIF-internal-clock-support.patch \
file://0006-uboot-Silk-disable-dcache-until-fixed.patch \
"
SRC_URI_append_silk = " \
file://0001-uboot-Silk-board-support.patch \
file://0004-uboot-porter-board-support.patch \
file://0005-uboot-serial-sh-SCIF-internal-clock-support.patch \
file://0006-uboot-Silk-disable-dcache-until-fixed.patch \
"

# MiniMonitor requires u-boot.srec
UBOOT_SREC ?= "u-boot.srec"
UBOOT_SREC_SYMLINK ?= "u-boot-${MACHINE}.srec"
UBOOT_SREC_IMAGE ?= "u-boot-${MACHINE}-${PV}-${PR}.srec"

do_deploy_append() {
install ${S}/${UBOOT_SREC} ${DEPLOYDIR}/${UBOOT_SREC_IMAGE}

cd ${DEPLOYDIR}
rm -f ${UBOOT_SREC} ${UBOOT_SREC_SYMLINK}
ln -sf ${UBOOT_SREC_IMAGE} ${UBOOT_SREC}
ln -sf ${UBOOT_SREC_IMAGE} ${UBOOT_SREC_SYMLINK}
}


SRC_URI_append_lcb = " \
    file://0001-arm-rmobile-Add-SILK-board-support.patch \
    file://0002-arm-rmobile-Add-Porter-board-support.patch \
    file://0003-serial-serial-sh-SCIF-internal-clock-source-support.patch \
    file://0004-ARM-cpu-Add-ARMv7-barrier-operations-support.patch \
    file://0007-gpio-sh-pfc-fix-gpio-input-read.patch \
    file://0008-serial-serial-sh-SCIFA-interface-for-R-Car-Gen2-SoCs.patch \
    file://0009-arm-rmobile-Add-r8a7790-stout-board-support.patch \
    file://0010-Support-R-Car-Gen2-USB-PHY.patch \
    file://0011-enable-rmobile-usb-phy-on-silk.patch \
"
