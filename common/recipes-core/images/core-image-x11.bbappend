IMAGE_INSTALL_append = " \
	packagegroup-renesas-core \
"

REQUIRED_DISTRO_FEATURES = "x11"
CONFLICT_DISTRO_FEATURES = "wayland"