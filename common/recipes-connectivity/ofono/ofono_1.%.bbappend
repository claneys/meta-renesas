DEPENDS_remove += "bluez4"
DEPENDS_append += "${@base_contains('DISTRO_FEATURES', 'bluetooth','bluez5', '', d)}"