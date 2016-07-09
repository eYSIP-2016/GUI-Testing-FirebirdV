#!/bin/bash

for sysdevpath in $(find /sys/bus/usb/devices/usb*/ -name dev); do
    (
        syspath="${sysdevpath%/dev}"
        devname="$(udevadm info -q name -p $syspath)"
        [[ "$devname" == *"/dev" ]] && continue
        eval "$(udevadm info -q property --export -p $syspath)"
        [[ -z "$ID_SERIAL" ]] && continue
	$digit = "1"
       	if [ "$ID_SERIAL" = "Nex_Robotics_NEX-USB-ISP_avrstk500" ]; then
		echo "$devname"
	fi
    )
done
