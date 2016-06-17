#!/bin/bash

# Environment setup script for CogentEmbedded Renesas BSP
# This script based on envsetup script from Yocto Layer of
# Automotive Grade Linux Distribution (https://gerrit.automotivelinux.org)

if [ -z $1 ]; then
    echo -e "Usage: source envsetup.sh <porter|porter-ext01|silk|stout> [build dir]"
    return 1
fi

echo "Checking for proprietary driver modules..."

PROPRIETARY_DIR=$PWD/proprietary


if [ ! "$(ls -A $PROPRIETARY_DIR 2>/dev/null)" ]; then
    echo "Failed to find proprietary drivers"
    echo "Download it from http://www.renesas.com/secret/r_car_download/rcar_demoboard.jsp and install to \"$PROPRIETARY_DIR\" directory"
    return 1
fi

MACHINE="$1"
echo "MACHINE=$MACHINE"

EULA_ACCEPT=0

case "$MACHINE" in
        "porter")
            MACHINE="porter"
            if [ ! -d "$TEMPLATECONF" ]; then
                # set template conf for R-Car M2 Porter board
                TEMPLATECONF="$PWD/meta-renesas/meta-rcar-gen2/templates/$MACHINE/conf"
            fi
            ;;
	"porter-ext01")
            MACHINE="porter"
            if [ ! -d "$TEMPLATECONF" ]; then
                # set template conf for R-Car M2 Porter with Extenstion board 01
                TEMPLATECONF="$PWD/meta-renesas/meta-rcar-gen2/templates/porter-ext01/conf"
            fi
            ;;
	"silk")
            MACHINE="silk"
            if [ ! -d "$TEMPLATECONF" ]; then
                # set template conf for R-Car E2 Silk board
                TEMPLATECONF="$PWD/meta-renesas/meta-rcar-gen2/templates/$MACHINE/conf"
            fi
            ;;
	"stout")
            MACHINE="stout"
            if [ ! -d "$TEMPLATECONF" ]; then
                # set template conf for R-Car H2 Stout board
                TEMPLATECONF="$PWD/meta-renesas/meta-rcar-gen2/templates/$MACHINE/conf"
            fi
            ;;
        *)
            # nothing to do here
            echo "ERR: '$MACHINE' is not tested by Cogent Embedded"
	    return 1
            ;;
esac

echo "TEMPALTECONF=$TEMPLATECONF"

echo "envsetup: Set '$MACHINE' as MACHINE."
export MACHINE

echo "Populate BSP with proprietary drivers..."

pushd $PWD > /dev/null
cd meta-renesas/meta-rcar-gen2
source copy_gfx_software_$MACHINE.sh $PROPRIETARY_DIR
source copy_mm_software_lcb.sh $PROPRIETARY_DIR
popd > /dev/null


# fallback
if [ ! -d "$TEMPLATECONF" ]; then
   # Allow to use templates at poky/meta-yocto/conf
   TEMPLATECONF="$PWD/poky/meta-yocto/conf"
fi

echo "envsetup: Using templates for local.conf & bblayers.conf from :"
echo "          '$TEMPLATECONF'"
export TEMPLATECONF

if [ -n "$2" ]; then
  BUILD_DIR="$2"
else
  BUILD_DIR=build
fi

echo "envsetup: Setup build environment for poky/oe."
echo -e

source poky/oe-init-build-env $BUILD_DIR

if [ -n "$DL_DIR" ]; then
        BB_ENV_EXTRAWHITE="$BB_ENV_EXTRAWHITE DL_DIR"
fi

if [ -n "$SSTATE_DIR" ]; then
        BB_ENV_EXTRAWHITE="$BB_ENV_EXTRAWHITE SSTATE_DIR"
fi

export BB_ENV_EXTRAWHITE

unset TEMPLATECONF
