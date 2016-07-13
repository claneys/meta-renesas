FILESEXTRAPATHS_append := "${THISDIR}/files:"

SRC_URI_append = "\
    file://bluez5.init \
    file://main.conf \
"

inherit update-rc.d

INITSCRIPT_PACKAGES = "${PN}"
INITSCRIPT_NAME_${PN} = "${PN}"
INITSCRIPT_PARAMS_${PN} = "start 16 5 . stop 01 0 1 6 ."

do_install_append() {
    install -d ${D}/${sysconfdir}/dbus-1/system.d
    install -m 644 ${S}/src/bluetooth.conf ${D}/${sysconfdir}/dbus-1/system.d

    install -d ${D}/etc/init.d
    install -m 0755 ${WORKDIR}/bluez5.init ${D}${sysconfdir}/init.d/bluez5

    install -d ${D}${sysconfdir}/bluetooth/
    install -m 0644 ${WORKDIR}/main.conf ${D}/${sysconfdir}/bluetooth/
}