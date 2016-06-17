include backports.inc

COMPAT_WIRELESS_VERSION = "4.2.6-1"

SRC_URI = " \
    http://www.kernel.org/pub/linux/kernel/projects/backports/stable/v4.2.6/backports-${COMPAT_WIRELESS_VERSION}.tar.xz;name=tarball \
    file://0001-wlcore_sdio-don-t-care-about-kernel-version.patch \
    file://0002-TI-ST-line-discipline-and-KIM-driver.patch \
    file://0003-ti-st-add-device-tree-support.patch \
    file://0004-btwilink-add-minimal-device-tree-support.patch \
"

do_configure() {
    cd ${S}
    unset CC CPP CCLD

    echo "CPTCFG_BPAUTO_CRYPTO_CCM=y" > defconfigs/newconfig
    echo "CPTCFG_BPAUTO_AVERAGE=y" >> defconfigs/newconfig
    echo "CPTCFG_CFG80211=m" >> defconfigs/newconfig
    echo "CPTCFG_CFG80211_DEFAULT_PS=y" >> defconfigs/newconfig
    echo "CPTCFG_CFG80211_DEBUGFS=y" >> defconfigs/newconfig
    echo "CPTCFG_CFG80211_WEXT=y" >> defconfigs/newconfig
    echo "CPTCFG_MAC80211=m" >> defconfigs/newconfig
    echo "CPTCFG_MAC80211_LEDS=y" >> defconfigs/newconfig
    echo "CPTCFG_MAC80211_MESH=y" >> defconfigs/newconfig
    echo "CPTCFG_WLAN=y" >> defconfigs/newconfig

    echo "CPTCFG_WL_TI=y" >> defconfigs/newconfig
    echo "CPTCFG_WL18XX=m" >> defconfigs/newconfig
    echo "CPTCFG_WLCORE=m" >> defconfigs/newconfig
    echo "CPTCFG_MMC=y" >> defconfigs/newconfig
    echo "CPTCFG_WLCORE_SDIO=m" >> defconfigs/newconfig
    echo "# CPTCFG_WILINK_PLATFORM_DATA is not set" >> defconfigs/newconfig

    echo "CPTCFG_BT=m" >> defconfigs/newconfig
    echo "CPTCFG_BT_RFCOMM=m" >> defconfigs/newconfig
    echo "CPTCFG_BT_RFCOMM_TTY=y" >> defconfigs/newconfig
    echo "CPTCFG_BT_BNEP=m" >> defconfigs/newconfig
    echo "CPTCFG_BT_BNEP_MC_FILTER=y" >> defconfigs/newconfig
    echo "CPTCFG_BT_BNEP_PROTO_FILTER=y" >> defconfigs/newconfig
    echo "CPTCFG_BT_HIDP=m" >> defconfigs/newconfig
    echo "CPTCFG_BT_LE=y" >> defconfigs/newconfig
    echo "CPTCFG_BT_WILINK=m" >> defconfigs/newconfig
    echo "CPTCFG_TI_ST=m" >> defconfigs/newconfig

    oe_runmake defconfig-newconfig
}

SRC_URI[tarball.md5sum] = "3f978eb56473d9289cf21ebbcb5aa80b"
SRC_URI[tarball.sha256sum] = "0b418f9f682fc49669b774f063bb0e2444324a2df3c60e753fcb7a22d350381a"