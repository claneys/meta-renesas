RRECOMMENDS_${PN}_remove = "bluez4"
RRECOMMENDS_${PN}_append = "${@base_contains('DISTRO_FEATURES', 'bluetooth', 'bluez5', '', d)}"