SUMMARY = "Startup script to initialize GPS HW"
LICENSE = "CLOSED"

SRC_URI = "file://init"

S = "${WORKDIR}"

do_install() {
	install -d ${D}/${sysconfdir}/init.d
	install -m755 ${WORKDIR}/init ${D}/${sysconfdir}/init.d/rc.gps
}

inherit allarch update-rc.d

INITSCRIPT_NAME = "rc.gps"
INITSCRIPT_PARAMS = "start 8 5 2 . stop 21 0 1 6 ."
