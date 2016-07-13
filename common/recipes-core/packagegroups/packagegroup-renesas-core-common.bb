DESCRIPTION = "The minimal set of common packages required by Renesas BSP"
LICENSE = "MIT"

inherit packagegroup

RDEPENDS_packagegroup-renesas-core-common = "\
    e2fsprogs \
    e2fsprogs-e2fsck \
    e2fsprogs-mke2fs \
    e2fsprogs-tune2fs \
    libcomerr \
    libe2p \
    libext2fs \
    libss \
    dbus \
    wpa-supplicant \
    backports \
    kernel-module-cfg80211 \
    kernel-module-compat \
    kernel-module-mac80211 \
    mm-init \
    can-utils \
    libsocketcan \
    libgstapp-1.0 \
    gstreamer1.0-plugins-base-app \
    udev-rules \
    pulseaudio \
    pulseaudio-server \
    pulseaudio-misc \
    pulseaudio-module-cli \
    pulseaudio-module-remap-sink \
    pulseaudio-module-remap-source \
    alsa-plugins \
"

BLUEZPACKAGES = "${@ '\
    bluez4 \
    bluez4-testtools \
    pulseaudio-module-bluez4-device \
    pulseaudio-module-bluez4-discover \
' if 'bluez4' in '${DISTRO_FEATURES}' else '\
    bluez5 \
    bluez5-testtools \
    pulseaudio-module-bluez5-device \
    pulseaudio-module-bluez5-discover \
' }"

RDEPENDS_packagegroup-renesas-core-common_append_porter = '${@ " \
    gps-init \
    si-tools \
    linux-firmware-wl18xx \
    sllin \
    radio-init \
    ti-bt \
    ti-bt-firmware \
    ${BLUEZPACKAGES} \
    pulseaudio-module-bluetooth-discover \
    pulseaudio-module-bluetooth-policy \
" if 'porter-ext01' in '${MACHINE_FEATURES}' else ""}'