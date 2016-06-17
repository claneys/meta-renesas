DESCRIPTION = "The set of package groups required by Renesas BSP"
LICENSE = "MIT"

inherit packagegroup

RDEPENDS_${PN} += "\
    packagegroup-renesas-core-common \
    "