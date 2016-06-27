require linux.inc
require linux-dtb.inc
require linux-dtb-append.inc
require ../../include/gles-control.inc
require ../../include/multimedia-control.inc

DESCRIPTION = "Linux kernel for the R-Car Generation 2 based board"
COMPATIBLE_MACHINE = "(alt|gose|koelsch|lager|porter|silk|stout)"

PV_append = "+git${SRCREV}"

RENESAS_BACKPORTS_URL="git://git.kernel.org/pub/scm/linux/kernel/git/horms/renesas-backport.git"
SRCREV = "165e12ce2d7839e755debbec78dfa43b54345275"
SRC_URI = " \
    ${RENESAS_BACKPORTS_URL};protocol=git;branch=bsp/v3.10.31-ltsi/rcar-gen2-1.9.7 \
    file://0001-arm-lager-Add-vmalloc-384M-to-bootargs-of-DTS.patch \
    file://0001-arm-koelsch-Add-vmalloc-384M-to-bootargs-of-DTS.patch \
    file://0001-arm-alt-Add-vmalloc-384M-to-bootargs-of-DTS.patch \
    file://0001-arm-gose-Add-vmalloc-384M-to-bootargs-of-DTS.patch \
"

SRC_URI_append = " \
    ${@' file://drm-rcar-du.cfg' \
    if '${USE_MULTIMEDIA}' == '0' or '${USE_GLES_WAYLAND}' == '0' else ''} \
"

SRC_URI_append_lcb = " \
    file://0001-ARM-rmobile-Add-SILK-board-support.patch \
    file://0002-ARM-shmobile-r8a7791-add-CAN-DT-support.patch \
    file://0003-can-add-Renesas-R-Car-CAN-driver.patch \
    file://0004-sh-pfc-r8a7791-add-CAN-pin-groups.patch \
    file://0005-sh-pfc-r8a7791-fix-CAN-pin-groups.patch \
    file://0006-can-rcar_can-support-all-input-clocks.patch \
    file://0007-can-rcar_can-document-device-tree-bindings.patch \
    file://0008-can-rcar_can-add-device-tree-support.patch \
    file://0009-ARM-rmobile-Add-Porter-board-support.patch \
    file://0010-i2c-rcar-Revert-i2c-rcar-Support-ACK-by-HW-auto-rest.patch \
    file://0011-i2c-busses-rcar-Workaround-arbitration-loss-error.patch \
    file://0012-gpu-rcar-du-add-RGB-connector.patch \
    file://0013-gpu-rcar-du-Set-interlace-to-false-by-default-for-r8.patch \
    file://0014-ARM-shmobile-defconfig-Enable-SCI-DMA-support.patch \
    file://0015-ARM-shmobile-defconfig-Enable-Bluetooth.patch \
    file://0016-ARM-shmobile-defconfig-Add-ATAG-DTB-compatibility.patch \
    file://0018-media-V4L-Add-mem2mem-ioctl-and-file-operation-helpe.patch \
    file://0019-V4L2-platform-Add-Renesas-R-Car-JPEG-codec-driver.patch \
    file://0020-devicetree-bindings-Document-Renesas-R-Car-JPEG-Proc.patch \
    file://0021-MAINTAINERS-V4L2-PLATFORM-Add-entry-for-Renesas-JPEG.patch \
    file://0022-ARM-shmobile-r8a7790-Add-JPU-clock-dt-and-CPG-define.patch \
    file://0023-ARM-shmobile-r8a7791-Add-JPU-clock-dt-and-CPG-define.patch \
    file://0024-ARM-shmobile-r8a7790-Add-JPU-device-node.patch \
    file://0025-ARM-shmobile-r8a7791-Add-JPU-device-node.patch \
    file://stout/01-da9063_rtc/0001-rtc-da9063-RTC-driver.patch \
    file://stout/02-da9063_mfd/0001-mfd-da9063-Add-IRQ-support.patch \
    file://stout/02-da9063_mfd/0003-mfd-da9063-Add-support-for-production-silicon-varian.patch \
    file://stout/02-da9063_mfd/0004-mfd-da9063-Upgrade-of-register-definitions-to-suppor.patch \
    file://stout/02-da9063_mfd/0005-mfd-da9063-Add-support-for-AD-silicon-variant.patch \
    file://stout/02-da9063_mfd/0006-mfd-da9063-Get-irq-base-dynamically-before-registeri.patch \
    file://stout/02-da9063_mfd/0007-mfd-da9063-Add-device-tree-support.patch \
    file://stout/03-da9063_regulator/0001-regulator-da9063-Add-Dialog-DA9063-voltage-regulator.patch \
    file://stout/03-da9063_regulator/0002-regulator-da9063-Optimize-da9063_set_current_limit-i.patch \
    file://stout/03-da9063_regulator/0003-regulator-da9063-Use-IS_ERR-to-check-return-value-of.patch \
    file://stout/03-da9063_regulator/0004-regulator-da9063-Statize-da9063_ldo_lim_event.patch \
    file://stout/03-da9063_regulator/0005-regulator-da9063-Fix-PTR_ERR-ERR_PTR-mismatch.patch \
    file://stout/03-da9063_regulator/0006-regulator-da9063-Add-missing-initialization-of-da906.patch \
    file://stout/03-da9063_regulator/0007-regulator-da9063-use-devm_regulator_register.patch \
    file://stout/03-da9063_regulator/0008-regulator-da9052-Revert-se-apply_-reg-bit-with-regma.patch \
    file://stout/03-da9063_regulator/0010-regulator-da9063-Bug-fix-when-setting-max-voltage-on.patch \
    file://stout/03-da9063_regulator/0011-regulator-da9063-Use-of_get_child_by_name.patch \
    file://stout/03-da9063_regulator/0012-regulator-da9063-Add-missing-of_node_put.patch \
    file://stout/03-da9063_regulator/0013-regulator-da9063-Do-not-hardcode-return-values.patch \
    file://stout/03-da9063_regulator/0014-regulator-da9063-Remove-redundant-error-message.patch \
    file://stout/03-da9063_regulator/0015-regulator-da9063-fix-assignment-of-da9063_reg_matche.patch \
    file://stout/03-da9063_regulator/0016-regulator-da9063-Do-not-transform-local-IRQ-to-virtu.patch \
    file://stout/04-da9063_wdt/0001-watchdog-Add-DA9063-PMIC-watchdog-driver.patch \
    file://stout/04-da9063_wdt/0002-watchdog-da9063-Add-restart-handler-support.patch \
    file://stout/05-regmap/0001-regmap-core-allow-a-virtual-range-to-cover-its-own-d.patch \
    file://stout/05-regmap/0002-regmap-irq-Allow-to-acknowledge-masked-interrupts-du.patch \
    file://stout/06-reboot/0001-kernel-sys.c-sys_reboot-fix-malformed-panic-message.patch \
    file://stout/06-reboot/0002-reboot-remove-stable-friendly-PF_THREAD_BOUND-define.patch \
    file://stout/06-reboot/0003-reboot-move-shutdown-reboot-related-functions-to-ker.patch \
    file://stout/06-reboot/0004-reboot-checkpatch.pl-the-new-kernel-reboot.c-file.patch \
    file://stout/06-reboot/0005-reboot-arm-prepare-reboot_mode-for-moving-to-generic.patch \
    file://stout/06-reboot/0006-reboot-x86-prepare-reboot_mode-for-moving-to-generic.patch \
    file://stout/06-reboot/0007-reboot-unicore32-prepare-reboot_mode-for-moving-to-g.patch \
    file://stout/06-reboot/0008-reboot-move-arch-x86-reboot-handling-to-generic-kern.patch \
    file://stout/06-reboot/0009-kernel-reboot.c-re-enable-the-function-of-variable-r.patch \
    file://stout/06-reboot/0010-kexec-migrate-to-reboot-cpu.patch \
    file://stout/06-reboot/0011-kernel-reboot.c-convert-simple_strtoul-to-kstrtoint.patch \
    file://stout/06-reboot/0012-kernel-add-support-for-kernel-restart-handler-call-c.patch \
    file://stout/06-reboot/0013-kernel-reboot.c-add-orderly_reboot-for-graceful-rebo.patch \
    file://stout/06-reboot/0014-reboot-arm-change-reboot_mode-to-use-enum-reboot_mod.patch \
    file://stout/06-reboot/0015-arm-support-restart-through-restart-handler-call-cha.patch \
    file://stout/07-r8a7790-stout/0001-sh-pfc-r8a7790-add-CAN-pin-groups.patch \
    file://stout/07-r8a7790-stout/0002-ARM-shmobile-r8a7790-add-CAN-DT-support.patch \
    file://stout/07-r8a7790-stout/0003-arm-shmobile-fix-compile-warnings.patch \
    file://stout/07-r8a7790-stout/0004-drm-rcar-du-parse-dt-adv7511-i2c-address.patch \
    file://stout/07-r8a7790-stout/0005-Fix-ADV7511-subchips-offsets.patch \
    file://stout/07-r8a7790-stout/0006-usb-xhci-rcar-Change-RCar-Gen2-usb3-firmware-to-upstream-name.patch \
    file://stout/07-r8a7790-stout/0007-xhci-rcar-add-firmware-for-R-Car-H2-M2-USB-3.0-host-.patch \
    file://stout/07-r8a7790-stout/0008-spi-sh-msiof-request-gpios-for-cs-gpios.patch \
    file://stout/07-r8a7790-stout/0009-sh-pfc-r8a7790-add-VIN0-1-_b-pins-support.patch \
    file://stout/07-r8a7790-stout/0010-media-i2c-soc_camera-Add-ov10635-sensor-driver.patch \
    file://stout/07-r8a7790-stout/0012-media-i2c-soc_camera-Add-MAX9272-ov10635-camera-driv.patch \
    file://stout/07-r8a7790-stout/0013-ethernet-ravb-introduce-alternative-PHY-MDIO-address.patch \
    file://stout/07-r8a7790-stout/0020-arm-rmobile-Add-R8A7790-stout-board-support.patch \
    file://stout/07-r8a7790-stout/0022-arm-rmobile-Add-R8A7790-stout-full-board-support.patch \
    file://stout/07-r8a7790-stout/0023-arm-rmobile-Add-R8A7790-stout-view-board-support.patch \
    file://stout/07-r8a7790-stout/0024-arm-rmobile-Add-R8A7790-stout-had-board-support.patch \
    file://pwm/0001-pwm-Add-pwm_get_polarity-helper-function.patch \
    file://pwm/0002-pwm-Add-sysfs-interface.patch \
    file://pwm/0003-pwm-Add-the-pwm_is_enabled-helper.patch \
    file://pwm/0004-pwm-Make-use-of-pwm_get_xxx-helpers-where-appropriat.patch \
    file://pwm/0005-pwm-sysfs-Properly-convert-from-enum-to-string.patch \
    file://pwm/0006-pwm-sysfs-Remove-unnecessary-temporary-variable.patch \
    file://pwm/0007-pwm-Add-support-for-R-Car-PWM-Timer.patch \
    file://pwm/0008-pwm-Add-device-tree-binding-document-for-R-Car-PWM-T.patch \
    file://pwm/0009-pwm-pwm-rcar-Revise-the-device-tree-binding-document.patch \
    file://pwm/0010-pinctrl-sh-pfc-r8a7790-Add-PWM-pin-groups-and-functi.patch \
    file://pwm/0011-ARM-shmobile-r8a7790-add-PWM-DT-support.patch \
    file://0026-R8A7790-add-MLP-clock.patch \
    file://0027-R8A7790-add-MLP-to-dtsi.patch \
    file://0028-R8A7790-add-mlp3pin-pinmux.patch \
    file://0029-R8A7791-add-MLP-clock.patch \
    file://0030-R8A7791-add-MLP-to-dtsi.patch \
    file://0031-R8A7791-add-MLP-pin-mux-3-pin-mode-and-fix-typo.patch \
    file://0032-AVB-support-Lager-Koelsch.patch \
    file://0033-AVB-support-Porter.patch \
    file://0034-Renesas-AVB-disable-Receive-FIFO-full-warning.patch \
    file://0035-add-avb-clock-to-7791.dtsi.patch \
    file://vin/0001-media-soc_camera-add-10bit-format-for-rcar_vin.patch \
    file://vin/0003-media-rcar_vin-copy-flags-from-pdata.patch \
    file://vin/0004-media-rcar_vin-add-devicetree-support.patch \
    file://vin/0005-media-soc_camera-remove-uneeded-semicolons.patch \
    file://vin/0006-media-platform-soc_camera-drop-owner-assignment-from.patch \
    file://vin/0007-media-rcar_vin-Add-DT-support-for-r8a7793-and-r8a779.patch \
    file://vin/0008-media-rcar_vin-Update-device_caps-and-capabilities-i.patch \
    file://vin/0009-media-soc_camera-use-vb2_ops_wait_prepare-finish-hel.patch \
    file://vin/0012-media-rcar-vin-Don-t-implement-empty-optional-clock-.patch \
    file://vin/0014-media-constify-of_device_id-array.patch \
    file://vin/0015-media-rcar-vin-use-monotonic-timestamps.patch \
    file://vin/0016-media-media-rcar_vin-fill-in-bus_info-field.patch \
    file://vin/0017-media-media-rcar_vin-Reject-videobufs-that-are-too-s.patch \
    file://vin/0018-media-rcar_vin-propagate-querystd-error-upstream.patch \
    file://vin/0019-media-rcar_vin-update_state_on_stop_streaming.patch \
    file://vin/0020-media-rcar_vin-add-V4L2_PIX_FMT_GREY-format.patch \
    file://vin/0021-media-rcar_vin-fix-NV16-scaling-check.patch \
    file://0036-R-Car-H2-Add-IMR-X2-V4L2-M2M-driver.patch \
    file://fixed_phy/0001-net-phy-breakdown-PHY_-_FEATURES-defines.patch \
    file://fixed_phy/0002-net-phy-decouple-PHY-id-and-PHY-address-in-fixed-PHY.patch \
    file://fixed_phy/0003-net-phy-extend-fixed-driver-with-fixed_phy_register.patch \
    file://fixed_phy/0004-net-of_mdio-factor-PHY-registration-from-of_mdiobus_.patch \
    file://fixed_phy/0005-net-of_mdio-use-PHY_MAX_ADDR-constant.patch \
    file://fixed_phy/0006-net-of_mdio-do-not-overwrite-PHY-interrupt-configura.patch \
    file://fixed_phy/0007-net-of_mdio-parse-max-speed-property-to-set-PHY-supp.patch \
    file://fixed_phy/0008-phylib-Add-of_phy_attach.patch \
    file://fixed_phy/0009-net-of_mdio-fix-of_set_phy_supported-after-driver-pr.patch \
    file://fixed_phy/0010-of_mdio-fix-phy-interrupt-passing.patch \
    file://fixed_phy/0011-of_mdio-Allow-the-DT-to-specify-the-phy-ID-and-avoid.patch \
    file://fixed_phy/0012-of-provide-a-binding-for-fixed-link-PHYs.patch \
    file://fixed_phy/0013-of-mdio-remove-of_phy_connect_fixed_link.patch \
    file://fixed_phy/0014-net-of_mdio-factor-out-code-to-parse-a-phy-s-reg-pro.patch \
    file://fixed_phy/0015-net-of_mdio-add-of_mdiobus_link_phydev.patch \
    file://fixed_phy/0016-net-of_mdio-don-t-store-the-length-of-a-property-if-.patch \
    file://fixed_phy/0017-net-of_mdio-use-int-type-for-address-variable.patch \
    file://fixed_phy/0018-of-of_mdio-export-symbol-of_mdiobus_link_phydev.patch \
    file://fixed_phy/0019-of-mdio-fixup-of_phy_register_fixed_link-parsing-of-.patch \
    file://fixed_phy/0020-net-fix-circular-dependency-in-of_mdio-code.patch \
    file://fixed_phy/0021-of-mdio-honor-flags-passed-to-of_phy_connect.patch \
    file://fixed_phy/0022-of-mdio-export-of_mdio_parse_addr.patch \
    file://0037-ravb-Add-fixed-link-support.patch \
    file://0038-sh-eth-Add-fixed-link-support.patch \
    file://0039-spi-sh-msiof-fix-incorrect-udelay.patch \
    file://0040-can-rcar_can-add-enable-and-standby-control-pins.patch \
    file://0049-Add-lsm9ds0-acc-gyro-mag-driver.patch \
    file://0051-TI-ADS111X-sigma-delta-ADC-driver.patch \
    file://0051a-Cleanup-TI-ADS111x-ADC-driver.patch \
    file://0059-crypto-aead-Add-crypto_aead_set_reqsize-helper.patch \
    file://sh-sci/0014-serial-sh-sci-Break-out-default-CTS-RTS-pin-setup.patch \
    file://sh-sci/0015-serial-sh-sci-Fix-default-RTS-handling.patch \
    file://sh-sci/0016-serial-sh-sci-Expose-default-CTS-pin.patch \
    file://sh-sci/0017-serial-sh-sci-Add-SCIFA-SCIFB-CTS-RTS-pin-setup.patch \
    file://sh-sci/0018-serial-sh-sci-Expose-SCIFA-SCIFB-CTS-pin.patch \
"


SRC_URI_append_porter = " \
    file://porter/0002-Porter-adapt-max9272-ov10635-driver-for-porter-exp-b.patch \
    file://porter/0003-Porter-add-LVDS-camera.patch \
    file://porter/0004-GPIO-CPLD-gpio-extender-driver.patch \
    file://porter/0012-regmap-Implemented-default-cache-sync-operation.patch \
    file://porter/0013-regmap-cache-Don-t-attempt-to-sync-non-writeable-reg.patch \
    file://porter/0014-ASoC-Add-SOC_DOUBLE_STS-macro.patch \
    file://porter/0015-ASoC-pcm3168a-Add-binding-document-for-pcm3168a-code.patch \
    file://porter/0016-ASoC-pcm3168a-Add-driver-for-pcm3168a-codec.patch \
    file://porter/0017-ASoC-PCM3168A-add-TDM-modes.patch \
    file://porter/0018-ASoC-PCM3168A-disable-16-bit-format.patch \
    file://porter/0019-ASoC-PCM3168A-enable-on-power-on.patch \
    file://porter/0020-ASoC-PCM3168A-merge-ADC-and-DAC-to-single-DAI.patch \
    file://porter/0021-ASoC-PCM3168A-disable-PM.patch \
    file://porter/0022-ASoC-fix-simple-card-do-not-respect-device-id.patch \
    file://porter/0023-SPI-GPIO-get-master-fix.patch \
    file://porter/0024-SPIDEV-extend-maximum-transfer-size.patch \
    file://porter/0025-Radio-add-si468x-to-spidev.patch \
    file://porter/0026-ASoC-add-dummy-Si468x-driver.patch \
    file://porter/0027-ASoC-R-Car-initial-TDM-support.patch \
    file://porter/0028-ASoC-rcar-correct-32bit-to-24-bit-sample-conv.patch \
    file://porter/0029-ASoC-R-Car-fix-debug-output.patch \
    file://porter/0030-R-Car-sound-disable-clock-hack.patch \
    file://porter/0031-ASoC-R-car-SSI-fix-SSI-slave-mode-setup-while-TDM-an.patch \
    file://porter/0032-ASoC-rsnd-care-SWSP-bit-for-TDM-non-TDM.patch \
    file://porter/0033-ASoC-R-car-SSI-fix-SSI-slave-mode-setup-while-TDM-an.patch \
    file://porter/0034-ASoC-RCar-fix-capture-in-TDM-mode.patch \
    file://porter/0032-mmc-Add-SDIO-function-devicetree-subnode-parsing.patch \
    file://porter/0038-Porter-LVDS-display-LQ123K1LG03.patch \
    file://porter/0060-Remove-delay-at-LVDS-camera-initialization.patch \
    file://porter/0099-Porter-add-separate-dts-for-ext01-extension-board.patch \
    file://porter/0100-Porter-ext01-add-dummy-regulator-to-select-48000-sou.patch \
"

SRC_URI_append_porter = " file://porter.cfg"
SRC_URI_append_porter = '${@ " file://porter_ext01.cfg"  if 'porter-ext01' in '${MACHINE_FEATURES}' else "" }'

SRC_URI_append_stout = " file://stout.cfg"

KERNEL_DEVICETREE_append_stout = '${@ \
	" ${S}/arch/arm/boot/dts/r8a7790-stout-full.dts " if 'stout-full' in '${MACHINE_FEATURES}' else \
	" ${S}/arch/arm/boot/dts/r8a7790-stout-view.dts " if 'stout-view' in '${MACHINE_FEATURES}' else \
	" ${S}/arch/arm/boot/dts/r8a7790-stout-had.dts " if 'stout-had' in '${MACHINE_FEATURES}' else \
	""}'

KERNEL_DEVICETREE_append_porter = '${@ \
	" ${S}/arch/arm/boot/dts/r8a7791-porter-ext01.dts " if 'porter-ext01' in '${MACHINE_FEATURES}' else \
	" ${S}/arch/arm/boot/dts/r8a7791-porter-eavb.dts " if 'porter-tse' in '${MACHINE_FEATURES}' else \
	""}'

PATCHTOOL_rcar-gen2 = "git"

S = "${WORKDIR}/git"

KERNEL_DEFCONFIG = "shmobile_defconfig"

do_configure_prepend() {
    install -m 0644 ${S}/arch/${ARCH}/configs/${KERNEL_DEFCONFIG} ${WORKDIR}/defconfig || die "No default configuration for ${MACHINE} / ${KERNEL_DEFCONFIG} available."
}

do_install_append_porter() {
    if [ -f ${B}/arch/${ARCH}/boot/uImage+dtb ]; then
        install -m 644 ${B}/arch/${ARCH}/boot/uImage+dtb ${D}/boot/
    fi
}

PR="r1"