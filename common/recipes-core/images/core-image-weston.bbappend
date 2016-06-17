# ...do not build any GTK+
IMAGE_INSTALL_remove = "gtk+3-demo clutter-1.0-examples"

IMAGE_INSTALL_append = " \
	packagegroup-renesas-core \
"

REQUIRED_DISTRO_FEATURES = "wayland"
CONFLICT_DISTRO_FEATURES = "x11"