FILESEXTRAPATHS_append := "${THISDIR}/files:"

RDEPENDS_${PN} += " \
	 alsa-conf \
"

SRC_URI_append = " \
    file://0001-HACK-disable-priority-inheritance-as-it-causes-occas.patch \
    file://0002-Workaround-to-keep-clock-in-sync-when-playback-is-ac.patch \
    file://pulseaudio.init \
    file://rsnd-dai.0-ak4642-hifi.conf \
    file://hifi.conf \
    file://porter.hifi.conf \
    file://system.pa \
    file://daemon.conf \
    file://pcm3168a.conf \
"

inherit update-rc.d

INITSCRIPT_NAME = "pulseaudio"
INITSCRIPT_PARAMS = "defaults 30"

do_install_append() {
    install -d ${D}/etc/init.d
    install -d ${D}/etc/pulse
    install -d ${D}/usr/share/alsa/ucm/rsnd-dai.0-ak4642-hifi/

    cp -a ${WORKDIR}/pulseaudio.init ${D}/etc/init.d/pulseaudio

    cp -a ${WORKDIR}/system.pa ${D}/etc/pulse/system.pa
    cp -a ${WORKDIR}/daemon.conf ${D}/etc/pulse/daemon.conf

    cp -a ${WORKDIR}/rsnd-dai.0-ak4642-hifi.conf ${D}${datadir}/alsa/ucm/rsnd-dai.0-ak4642-hifi/rsnd-dai.0-ak4642-hifi.conf
    cp -a ${WORKDIR}/hifi.conf ${D}${datadir}/alsa/ucm/rsnd-dai.0-ak4642-hifi/hifi.conf
}

do_install_append_porter() {
    install -d ${D}/etc/init.d
    install -d ${D}/etc/pulse
    install -d ${D}/usr/share/alsa/ucm/ak464x/
    install -d ${D}/usr/share/alsa/cards/

    cp -a ${WORKDIR}/pulseaudio.init ${D}/etc/init.d/pulseaudio

    cp -a ${WORKDIR}/system.pa ${D}/etc/pulse/system.pa
    cp -a ${WORKDIR}/daemon.conf ${D}/etc/pulse/daemon.conf

    cp -a ${WORKDIR}/rsnd-dai.0-ak4642-hifi.conf ${D}${datadir}/alsa/ucm/ak464x/ak464x.conf
    cp -a ${WORKDIR}/porter.hifi.conf ${D}${datadir}/alsa/ucm/ak464x/hifi.conf
    cp -a ${WORKDIR}/pcm3168a.conf ${D}${datadir}/alsa/cards/pcm3168a.conf
}

FILES_${PN} += " \
	    ${datadir}/alsa/ucm \
	    ${datadir}/alsa/cards \
"


PACKAGECONFIG_remove = "bluez4"
PACKAGECONFIG_append = "bluez5"