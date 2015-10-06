#!/bin/bash

MM_PKG_REVISION="20150727"

MM_PKG_NAME="R-Car_Series_Evaluation_Software_Package"
MM_PKG_USERLAND="for_Linux"
MM_PKG_KERNEL="of_Linux_Drivers"

ZIP_1=${MM_PKG_NAME}_${MM_PKG_USERLAND}-${MM_PKG_REVISION}.zip
ZIP_2=${MM_PKG_NAME}_${MM_PKG_KERNEL}-${MM_PKG_REVISION}.zip

COPY_GFX_SCRIPT=copy_gfx_software_$1.sh
COPY_MM_SCRIPT=copy_mm_software_lcb.sh

test -f ${XDG_CONFIG_HOME:-~/.config}/user-dirs.dirs && source ${XDG_CONFIG_HOME:-~/.config}/user-dirs.dirs
DOWNLOAD_DIR=${XDG_DOWNLOAD_DIR:-$HOME/Downloads}

function copy_mm_packages() {
        if [ ! -d binary-tmp ]; then
                if [ -f $DOWNLOAD_DIR/$ZIP_1 -a -f $DOWNLOAD_DIR/$ZIP_2 ]; then
                        mkdir binary-tmp
                        cd binary-tmp
                        unzip -o $DOWNLOAD_DIR/$ZIP_1
                        unzip -o $DOWNLOAD_DIR/$ZIP_2
                        cd ..
                else
                        echo -n "The graphics and multimedia acceleration packages for "
                        echo -e "the R-Car M2 Porter board can be download from :"
                        echo -e "  <http://www.renesas.com/secret/r_car_download/rcar_demoboard.jsp>"
                        echo -e
                        echo -n "These 2 files from there should be store in your"
                        echo -e "'$DOWNLOAD_DIR' directory."
                        echo -e "  $ZIP_1"
                        echo -e "  $ZIP_2"
                        return -1
                fi
        fi

        if [ -f meta-renesas/meta-rcar-gen2/$COPY_GFX_SCRIPT ]; then
                cd meta-renesas/meta-rcar-gen2/
                ./$COPY_GFX_SCRIPT ../../binary-tmp
                cd ../..
        else
                echo "scripts to copy GFX drivers for '$1' not found."
                return -1
        fi

        if [ -f meta-renesas/meta-rcar-gen2/$COPY_MM_SCRIPT ]; then
                cd meta-renesas/meta-rcar-gen2/
                ./$COPY_MM_SCRIPT ../../binary-tmp
                cd ../..
        else
                echo "scripts to copy MM software for '$1' not found."
                return -1
        fi
}
