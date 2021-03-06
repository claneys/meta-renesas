#!/bin/sh

### Note: To run this script, please stand on meta-rcar-gen2 to run.

if [ ! -d $1/R-Car_Series_Evaluation_Software_Package_for_Linux ]
then
  tar -C $1 -zxf $1/R-Car_Series_Evaluation_Software_Package_for_Linux-*.tar.gz
fi

if [ ! -d $1/R-Car_Series_Evaluation_Software_Package_of_Linux_Drivers ]
then
  tar -C $1 -zxf $1/R-Car_Series_Evaluation_Software_Package_of_Linux_Drivers-*.tar.gz
fi

#cp $1/R-Car_Series_Evaluation_Software_Package_of_Linux_Drivers/sgx_gles2_e2_v115_eva/EVARTM0RC7794GLDE0001SL33C/EVA_SGX_KM_E2.tar.bz2 recipes-kernel/gles-module/gles-kernel-module/
#cp $1/R-Car_Series_Evaluation_Software_Package_for_Linux/sgx_gles2_e2_v115_eva/EVARTM0RC7794GLRE0001SL33C/release/EVA_r8a7794_linux_sgx_binaries_gles2.tar.bz2 recipes-graphics/gles-module/gles-user-module/

# There are two KM tarballs (gles2 and gles3), but they are identical.
# So we don'r care which one we will copy
RGX_KM=`find $1 -name *RGX_KM_H2.tar.bz2 | tail -1`
cp -r $RGX_KM recipes-kernel/gles-module/gles-kernel-module/RGX_KM_H2.tar.bz2


RGX_LIB=`find $1 -name *r8a7790_linux_rgx_binaries_gles2.tar.bz2 | tail -1`
cp -r $RGX_LIB recipes-graphics/gles-module/gles-user-module/r8a7790_linux_rgx_binaries_gles2.tar.bz2

RGX_LIB=`find $1 -name *r8a7790_linux_rgx_binaries_gles3.tar.bz2 | tail -1`
cp -r $RGX_LIB recipes-graphics/gles-module/gles-user-module/r8a7790_linux_rgx_binaries_gles3.tar.bz2
