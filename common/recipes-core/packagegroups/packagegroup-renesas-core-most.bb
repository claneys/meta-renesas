DESCRIPTION = "The set of packages required by MOST"
LICENSE = "MIT"

inherit packagegroup

RDEPENDS_packagegroup-renesas-core-most = '${@ " \
    most-drivers \
    most-drivers-initd \
    kernel-module-aim-sound \
    kernel-module-aim-cdev \
    kernel-module-aim-network \
    kernel-module-aim-v4l2   \
    kernel-module-dim2hdm \
    kernel-module-dim2h2   \
    kernel-module-mostcore \
" if 'most' in '${DISTRO_FEATURES}' else ""}'