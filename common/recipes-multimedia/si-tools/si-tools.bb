SUMMARY = "Tools for si46xx AM/FM/DAB radio chip"
SECTION = "multimedia"

LICENSE = "CLOSED"

PE = "1"
PV = "0.2"

SRC_URI = "file://si-tools.tar.gz \
"
SRC_URI_append_porter = "file://si_init \
"
S = "${WORKDIR}/si-tools"

do_install() {
	install -d ${D}${bindir}

	install -m 0755 si_ctl ${D}${bindir}
	install -m 0755 si_flash ${D}${bindir}
	install -m 0755 si_firmware_update ${D}${bindir}
}

do_install_append_porter() {
	cp ${WORKDIR}/si_init ${D}${bindir}
	chmod 0755 ${D}${bindir}/si_init
}