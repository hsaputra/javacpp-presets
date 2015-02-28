// Targeted by JavaCPP version 0.11-SNAPSHOT

package org.bytedeco.javacpp;

import java.nio.*;
import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.annotation.*;

public class freenect extends org.bytedeco.javacpp.presets.freenect {
    static { Loader.load(); }

// Parsed from <libfreenect/libfreenect.h>

/*
 * This file is part of the OpenKinect Project. http://www.openkinect.org
 *
 * Copyright (c) 2010 individual OpenKinect contributors. See the CONTRIB file
 * for details.
 *
 * This code is licensed to you under the terms of the Apache License, version
 * 2.0, or, at your option, the terms of the GNU General Public License,
 * version 2.0. See the APACHE20 and GPL2 files for the text of the licenses,
 * or the following URLs:
 * http://www.apache.org/licenses/LICENSE-2.0
 * http://www.gnu.org/licenses/gpl-2.0.txt
 *
 * If you redistribute this file in source form, modified or unmodified, you
 * may:
 *   1) Leave this header intact and distribute it under the same terms,
 *      accompanying it with the APACHE20 and GPL20 files, or
 *   2) Delete the Apache 2.0 clause and accompany it with the GPL2 file, or
 *   3) Delete the GPL v2 clause and accompany it with the APACHE20 file
 * In all cases you must keep the copyright notice intact and include a copy
 * of the CONTRIB file.
 *
 * Binary distributions must follow the binary distribution requirements of
 * either License.
 */

// #pragma once

// #include <stdint.h>

/* We need struct timeval */
// #ifdef _WIN32
// #include <winsock.h>
// #else
// #include <sys/time.h>
// #endif

// #ifdef __cplusplus
// #endif

/** Ticks per G for accelerometer as set per http://www.kionix.com/Product%20Sheets/KXSD9%20Product%20Brief.pdf */
public static final int FREENECT_COUNTS_PER_G = 819;

/** Maximum value that a uint16_t pixel will take on in the buffer of any of the FREENECT_DEPTH_MM or FREENECT_DEPTH_REGISTERED frame callbacks */
public static final int FREENECT_DEPTH_MM_MAX_VALUE = 10000;
/** Value indicating that this pixel has no data, when using FREENECT_DEPTH_MM or FREENECT_DEPTH_REGISTERED depth modes */
public static final int FREENECT_DEPTH_MM_NO_VALUE = 0;
/** Maximum value that a uint16_t pixel will take on in the buffer of any of the FREENECT_DEPTH_11BIT, FREENECT_DEPTH_10BIT, FREENECT_DEPTH_11BIT_PACKED, or FREENECT_DEPTH_10BIT_PACKED frame callbacks */
public static final int FREENECT_DEPTH_RAW_MAX_VALUE = 2048;
/** Value indicating that this pixel has no data, when using FREENECT_DEPTH_11BIT, FREENECT_DEPTH_10BIT, FREENECT_DEPTH_11BIT_PACKED, or FREENECT_DEPTH_10BIT_PACKED */
public static final int FREENECT_DEPTH_RAW_NO_VALUE = 2047;

/** Flags representing devices to open when freenect_open_device() is called.
 *  In particular, this allows libfreenect to grab only a subset of the devices
 *  in the Kinect, so you could (for instance) use libfreenect to handle audio
 *  and motor support while letting OpenNI have access to the cameras.
 *  If a device is not supported on a particular platform, its flag will be ignored. */
/** enum freenect_device_flags */
public static final int
	FREENECT_DEVICE_MOTOR  =  0x01,
	FREENECT_DEVICE_CAMERA =  0x02,
	FREENECT_DEVICE_AUDIO  =  0x04;

/** A struct used in enumeration to give access to serial numbers, so you can
 *  open a particular device by serial rather than depending on index.  This
 *  is most useful if you have more than one Kinect. */
public static class freenect_device_attributes extends Pointer {
    static { Loader.load(); }
    public freenect_device_attributes() { allocate(); }
    public freenect_device_attributes(int size) { allocateArray(size); }
    public freenect_device_attributes(Pointer p) { super(p); }
    private native void allocate();
    private native void allocateArray(int size);
    @Override public freenect_device_attributes position(int position) {
        return (freenect_device_attributes)super.position(position);
    }

	public native freenect_device_attributes next(); public native freenect_device_attributes next(freenect_device_attributes next); // Next device in the linked list
	@MemberGetter public native @Cast("const char*") BytePointer camera_serial();               // Serial number of camera or audio subdevice
}

/** Enumeration of available resolutions.
 *  Not all available resolutions are actually supported for all video formats.
 *  Frame modes may not perfectly match resolutions.  For instance,
 *  FREENECT_RESOLUTION_MEDIUM is 640x488 for the IR camera. */
/** enum freenect_resolution */
public static final int
	/** QVGA - 320x240 */
	FREENECT_RESOLUTION_LOW    = 0,
	/** VGA  - 640x480 */
	FREENECT_RESOLUTION_MEDIUM = 1,
	/** SXGA - 1280x1024 */
	FREENECT_RESOLUTION_HIGH   = 2,
	/** Dummy value to force enum to be 32 bits wide */
	FREENECT_RESOLUTION_DUMMY  = 2147483647;

/** Enumeration of video frame information states.
 *  See http://openkinect.org/wiki/Protocol_Documentation#RGB_Camera for more information. */
/** enum freenect_video_format */
public static final int
	/** Decompressed RGB mode (demosaicing done by libfreenect) */
	FREENECT_VIDEO_RGB             = 0,
	/** Bayer compressed mode (raw information from camera) */
	FREENECT_VIDEO_BAYER           = 1,
	/** 8-bit IR mode  */
	FREENECT_VIDEO_IR_8BIT         = 2,
	/** 10-bit IR mode */
	FREENECT_VIDEO_IR_10BIT        = 3,
	/** 10-bit packed IR mode */
	FREENECT_VIDEO_IR_10BIT_PACKED = 4,
	/** YUV RGB mode */
	FREENECT_VIDEO_YUV_RGB         = 5,
	/** YUV Raw mode */
	FREENECT_VIDEO_YUV_RAW         = 6,
	/** Dummy value to force enum to be 32 bits wide */
	FREENECT_VIDEO_DUMMY           = 2147483647;

/** Enumeration of depth frame states
 *  See http://openkinect.org/wiki/Protocol_Documentation#RGB_Camera for more information. */
/** enum freenect_depth_format */
public static final int
	/** 11 bit depth information in one uint16_t/pixel */
	FREENECT_DEPTH_11BIT        = 0,
	/** 10 bit depth information in one uint16_t/pixel */
	FREENECT_DEPTH_10BIT        = 1,
	/** 11 bit packed depth information */
	FREENECT_DEPTH_11BIT_PACKED = 2,
	/** 10 bit packed depth information */
	FREENECT_DEPTH_10BIT_PACKED = 3,
	/** processed depth data in mm, aligned to 640x480 RGB */
	FREENECT_DEPTH_REGISTERED   = 4,
	/** depth to each pixel in mm, but left unaligned to RGB image */
	FREENECT_DEPTH_MM           = 5,
	/** Dummy value to force enum to be 32 bits wide */
	FREENECT_DEPTH_DUMMY        = 2147483647;

/** Enumeration of flags to toggle features with freenect_set_flag() */
/** enum freenect_flag */
public static final int
	// values written to the CMOS register
	FREENECT_AUTO_EXPOSURE      =  1 << 14,
	FREENECT_AUTO_WHITE_BALANCE =  1 << 1,
	FREENECT_RAW_COLOR          =  1 << 4,
	// arbitrary bitfields to support flag combination
	FREENECT_MIRROR_DEPTH       =  1 << 16,
	FREENECT_MIRROR_VIDEO       =  1 << 17;

/** Possible values for setting each `freenect_flag` */
/** enum freenect_flag_value */
public static final int
	FREENECT_OFF = 0,
	FREENECT_ON  = 1;

/** Structure to give information about the width, height, bitrate,
 *  framerate, and buffer size of a frame in a particular mode, as
 *  well as the total number of bytes needed to hold a single frame. */
public static class freenect_frame_mode extends Pointer {
    static { Loader.load(); }
    public freenect_frame_mode() { allocate(); }
    public freenect_frame_mode(int size) { allocateArray(size); }
    public freenect_frame_mode(Pointer p) { super(p); }
    private native void allocate();
    private native void allocateArray(int size);
    @Override public freenect_frame_mode position(int position) {
        return (freenect_frame_mode)super.position(position);
    }

	/** unique ID used internally.  The meaning of values may change without notice.  Don't touch or depend on the contents of this field.  We mean it. */
	public native @Cast("uint32_t") int reserved(); public native freenect_frame_mode reserved(int reserved);
	/** Resolution this freenect_frame_mode describes, should you want to find it again with freenect_find_*_frame_mode(). */
	public native @Cast("freenect_resolution") int resolution(); public native freenect_frame_mode resolution(int resolution);
		public native int dummy(); public native freenect_frame_mode dummy(int dummy);
		public native @Cast("freenect_video_format") int video_format(); public native freenect_frame_mode video_format(int video_format);
		public native @Cast("freenect_depth_format") int depth_format(); public native freenect_frame_mode depth_format(int depth_format);
	/** Total buffer size in bytes to hold a single frame of data.  Should be equivalent to width * height * (data_bits_per_pixel+padding_bits_per_pixel) / 8 */
	public native int bytes(); public native freenect_frame_mode bytes(int bytes);
	/** Width of the frame, in pixels */
	public native short width(); public native freenect_frame_mode width(short width);
	/** Height of the frame, in pixels */
	public native short height(); public native freenect_frame_mode height(short height);
	/** Number of bits of information needed for each pixel */
	public native byte data_bits_per_pixel(); public native freenect_frame_mode data_bits_per_pixel(byte data_bits_per_pixel);
	/** Number of bits of padding for alignment used for each pixel */
	public native byte padding_bits_per_pixel(); public native freenect_frame_mode padding_bits_per_pixel(byte padding_bits_per_pixel);
	/** Approximate expected frame rate, in Hz */
	public native byte framerate(); public native freenect_frame_mode framerate(byte framerate);
	/** If 0, this freenect_frame_mode is invalid and does not describe a supported mode.  Otherwise, the frame_mode is valid. */
	public native byte is_valid(); public native freenect_frame_mode is_valid(byte is_valid);
}

/** Enumeration of LED states
 *  See http://openkinect.org/wiki/Protocol_Documentation#Setting_LED for more information. */
/** enum freenect_led_options */
public static final int
	/** Turn LED off */
	LED_OFF              = 0,
	/** Turn LED to Green */
	LED_GREEN            = 1,
	/** Turn LED to Red */
	LED_RED              = 2,
	/** Turn LED to Yellow */
	LED_YELLOW           = 3,
	/** Make LED blink Green */
	LED_BLINK_GREEN      = 4,
	// 5 is same as 4, LED blink Green
	/** Make LED blink Red/Yellow */
	LED_BLINK_RED_YELLOW = 6;


/** Enumeration of tilt motor status */
/** enum freenect_tilt_status_code */
public static final int
	/** Tilt motor is stopped */
	TILT_STATUS_STOPPED =  0x00,
	/** Tilt motor has reached movement limit */
	TILT_STATUS_LIMIT   =  0x01,
	/** Tilt motor is currently moving to new position */
	TILT_STATUS_MOVING  =  0x04;

/** Data from the tilt motor and accelerometer */
public static class freenect_raw_tilt_state extends Pointer {
    static { Loader.load(); }
    public freenect_raw_tilt_state() { allocate(); }
    public freenect_raw_tilt_state(int size) { allocateArray(size); }
    public freenect_raw_tilt_state(Pointer p) { super(p); }
    private native void allocate();
    private native void allocateArray(int size);
    @Override public freenect_raw_tilt_state position(int position) {
        return (freenect_raw_tilt_state)super.position(position);
    }

	/** Raw accelerometer data for X-axis, see FREENECT_COUNTS_PER_G for conversion */
	public native short accelerometer_x(); public native freenect_raw_tilt_state accelerometer_x(short accelerometer_x);
	/** Raw accelerometer data for Y-axis, see FREENECT_COUNTS_PER_G for conversion */
	public native short accelerometer_y(); public native freenect_raw_tilt_state accelerometer_y(short accelerometer_y);
	/** Raw accelerometer data for Z-axis, see FREENECT_COUNTS_PER_G for conversion */
	public native short accelerometer_z(); public native freenect_raw_tilt_state accelerometer_z(short accelerometer_z);
	/** Raw tilt motor angle encoder information */
	public native byte tilt_angle(); public native freenect_raw_tilt_state tilt_angle(byte tilt_angle);
	/** State of the tilt motor (stopped, moving, etc...) */
	public native @Cast("freenect_tilt_status_code") int tilt_status(); public native freenect_raw_tilt_state tilt_status(int tilt_status);
}

@Opaque public static class _freenect_context extends Pointer {
    public _freenect_context() { }
    public _freenect_context(Pointer p) { super(p); }
}
/** Holds information about the usb context. */
@Opaque public static class freenect_context extends Pointer {
    public freenect_context() { }
    public freenect_context(Pointer p) { super(p); }
}

@Opaque public static class _freenect_device extends Pointer {
    public _freenect_device() { }
    public _freenect_device(Pointer p) { super(p); }
}
/** Holds device information. */
@Opaque public static class freenect_device extends Pointer {
    public freenect_device() { }
    public freenect_device(Pointer p) { super(p); }
}

// usb backend specific section
/** Holds libusb-1.0 context */
@Opaque public static class freenect_usb_context extends Pointer {
    public freenect_usb_context() { }
    public freenect_usb_context(Pointer p) { super(p); }
}
//

/** If Win32, export all functions for DLL usage */
// #ifndef _WIN32
/** DLLExport information for windows, set to nothing on other platforms */
/** DLLExport information for windows, set to nothing on other platforms */
//   #define FREENECTAPI
// #else
//   #ifdef __cplusplus
//     #define FREENECTAPI extern "C" __declspec(dllexport)
//   #else
    // this is required when building from a Win32 port of gcc without being
    // forced to compile all of the library files (.c) with g++...
//     #define FREENECTAPI __declspec(dllexport)
//   #endif
// #endif

/** Enumeration of message logging levels */
/** enum freenect_loglevel */
public static final int
	/** Log for crashing/non-recoverable errors */
	FREENECT_LOG_FATAL = 0,
	/** Log for major errors */
	FREENECT_LOG_ERROR = 1,
	/** Log for warning messages */
	FREENECT_LOG_WARNING = 2,
	/** Log for important messages */
	FREENECT_LOG_NOTICE = 3,
	/** Log for normal messages */
	FREENECT_LOG_INFO = 4,
	/** Log for useful development messages */
	FREENECT_LOG_DEBUG = 5,
	/** Log for slightly less useful messages */
	FREENECT_LOG_SPEW = 6,
	/** Log EVERYTHING. May slow performance. */
	FREENECT_LOG_FLOOD = 7;

/**
 * Initialize a freenect context and do any setup required for
 * platform specific USB libraries.
 *
 * @param ctx Address of pointer to freenect context struct to allocate and initialize
 * @param usb_ctx USB context to initialize. Can be NULL if not using multiple contexts.
 *
 * @return 0 on success, < 0 on error
 */
public static native int freenect_init(@Cast("freenect_context**") PointerPointer ctx, freenect_usb_context usb_ctx);
public static native int freenect_init(@ByPtrPtr freenect_context ctx, freenect_usb_context usb_ctx);

/**
 * Closes the device if it is open, and frees the context
 *
 * @param ctx freenect context to close/free
 *
 * @return 0 on success
 */
public static native int freenect_shutdown(freenect_context ctx);

/** Typedef for logging callback functions */
public static class freenect_log_cb extends FunctionPointer {
    static { Loader.load(); }
    public    freenect_log_cb(Pointer p) { super(p); }
    protected freenect_log_cb() { allocate(); }
    private native void allocate();
    public native void call(freenect_context dev, @Cast("freenect_loglevel") int level, @Cast("const char*") BytePointer msg);
}

/**
 * Set the log level for the specified freenect context
 *
 * @param ctx context to set log level for
 * @param level log level to use (see freenect_loglevel enum)
 */
public static native void freenect_set_log_level(freenect_context ctx, @Cast("freenect_loglevel") int level);

/**
 * Callback for log messages (i.e. for rerouting to a file instead of
 * stdout)
 *
 * @param ctx context to set log callback for
 * @param cb callback function pointer
 */
public static native void freenect_set_log_callback(freenect_context ctx, freenect_log_cb cb);

/**
 * Calls the platform specific usb event processor
 *
 * @param ctx context to process events for
 *
 * @return 0 on success, other values on error, platform/library dependant
 */
public static native int freenect_process_events(freenect_context ctx);

/**
 * Calls the platform specific usb event processor until either an event occurs
 * or the timeout parameter time has passed.  If a zero timeval is passed, this
 * function will handle any already-pending events, then return immediately.
 *
 * @param ctx Context to process events for
 * @param timeout Pointer to a timeval containing the maximum amount of time to block waiting for events, or zero for nonblocking mode
 *
 * @return 0 on success, other values on error, platform/library dependant
 */
public static native int freenect_process_events_timeout(freenect_context ctx, timeval timeout);

/**
 * Return the number of kinect devices currently connected to the
 * system
 *
 * @param ctx Context to access device count through
 *
 * @return Number of devices connected, < 0 on error
 */
public static native int freenect_num_devices(freenect_context ctx);

/**
 * Scans for kinect devices and produces a linked list of their attributes
 * (namely, serial numbers), returning the number of devices.
 *
 * @param ctx Context to scan for kinect devices with
 * @param attribute_list Pointer to where this function will store the resultant linked list
 *
 * @return Number of devices connected, < 0 on error
 */
public static native int freenect_list_device_attributes(freenect_context ctx, @Cast("freenect_device_attributes**") PointerPointer attribute_list);
public static native int freenect_list_device_attributes(freenect_context ctx, @ByPtrPtr freenect_device_attributes attribute_list);

/**
 * Free the linked list produced by freenect_list_device_attributes().
 *
 * @param attribute_list Linked list of attributes to free.
 */
public static native void freenect_free_device_attributes(freenect_device_attributes attribute_list);

/**
 * Answer which subdevices this library supports.  This is most useful for
 * wrappers trying to determine whether the underlying library was built with
 * audio support or not, so the wrapper can avoid calling functions that do not
 * exist.
 *
 * @return Flags representing the subdevices that the library supports opening (see freenect_device_flags)
 */
public static native int freenect_supported_subdevices();

/**
 * Set which subdevices any subsequent calls to freenect_open_device()
 * should open.  This will not affect devices which have already been
 * opened.  The default behavior, should you choose not to call this
 * function at all, is to open all supported subdevices - motor, cameras,
 * and audio, if supported on the platform.
 *
 * @param ctx Context to set future subdevice selection for
 * @param subdevs Flags representing the subdevices to select
 */
public static native void freenect_select_subdevices(freenect_context ctx, @Cast("freenect_device_flags") int subdevs);

/**
 * Returns the devices that are enabled after calls to freenect_open_device()
 * On newer kinects the motor and audio are automatically disabled for now
 *
 * @param ctx Context to set future subdevice selection for
 * @return Flags representing the subdevices that were actually opened (see freenect_device_flags)
 */
public static native @Cast("freenect_device_flags") int freenect_enabled_subdevices(freenect_context ctx);

/**
 * Opens a kinect device via a context. Index specifies the index of
 * the device on the current state of the bus. Bus resets may cause
 * indexes to shift.
 *
 * @param ctx Context to open device through
 * @param dev Device structure to assign opened device to
 * @param index Index of the device on the bus
 *
 * @return 0 on success, < 0 on error
 */
public static native int freenect_open_device(freenect_context ctx, @Cast("freenect_device**") PointerPointer dev, int index);
public static native int freenect_open_device(freenect_context ctx, @ByPtrPtr freenect_device dev, int index);

/**
 * Opens a kinect device (via a context) associated with a particular camera
 * subdevice serial number.  This function will fail if no device with a
 * matching serial number is found.
 *
 * @param ctx Context to open device through
 * @param dev Device structure to assign opened device to
 * @param camera_serial Null-terminated ASCII string containing the serial number of the camera subdevice in the device to open
 *
 * @return 0 on success, < 0 on error
 */
public static native int freenect_open_device_by_camera_serial(freenect_context ctx, @Cast("freenect_device**") PointerPointer dev, @Cast("const char*") BytePointer camera_serial);
public static native int freenect_open_device_by_camera_serial(freenect_context ctx, @ByPtrPtr freenect_device dev, @Cast("const char*") BytePointer camera_serial);
public static native int freenect_open_device_by_camera_serial(freenect_context ctx, @ByPtrPtr freenect_device dev, String camera_serial);

/**
 * Closes a device that is currently open
 *
 * @param dev Device to close
 *
 * @return 0 on success
 */
public static native int freenect_close_device(freenect_device dev);

/**
 * Set the device user data, for passing generic information into
 * callbacks
 *
 * @param dev Device to attach user data to
 * @param user User data to attach
 */
public static native void freenect_set_user(freenect_device dev, Pointer user);

/**
 * Retrieve the pointer to user data from the device struct
 *
 * @param dev Device from which to get user data
 *
 * @return Pointer to user data
 */
public static native Pointer freenect_get_user(freenect_device dev);

/** Typedef for depth image received event callbacks */
public static class freenect_depth_cb extends FunctionPointer {
    static { Loader.load(); }
    public    freenect_depth_cb(Pointer p) { super(p); }
    protected freenect_depth_cb() { allocate(); }
    private native void allocate();
    public native void call(freenect_device dev, Pointer depth, @Cast("uint32_t") int timestamp);
}
/** Typedef for video image received event callbacks */
public static class freenect_video_cb extends FunctionPointer {
    static { Loader.load(); }
    public    freenect_video_cb(Pointer p) { super(p); }
    protected freenect_video_cb() { allocate(); }
    private native void allocate();
    public native void call(freenect_device dev, Pointer video, @Cast("uint32_t") int timestamp);
}
/** Typedef for stream chunk processing callbacks */
public static class freenect_chunk_cb extends FunctionPointer {
    static { Loader.load(); }
    public    freenect_chunk_cb(Pointer p) { super(p); }
    protected freenect_chunk_cb() { allocate(); }
    private native void allocate();
    public native void call(Pointer buffer, Pointer pkt_data, int pkt_num, int datalen, Pointer user_data);
}


/**
 * Set callback for depth information received event
 *
 * @param dev Device to set callback for
 * @param cb Function pointer for processing depth information
 */
public static native void freenect_set_depth_callback(freenect_device dev, freenect_depth_cb cb);

/**
 * Set callback for video information received event
 *
 * @param dev Device to set callback for
 * @param cb Function pointer for processing video information
 */
public static native void freenect_set_video_callback(freenect_device dev, freenect_video_cb cb);

/**
 * Set callback for depth chunk processing
 *
 * @param dev Device to set callback for
 * @param cb Function pointer for processing depth chunk
 */
public static native void freenect_set_depth_chunk_callback(freenect_device dev, freenect_chunk_cb cb);

/**
 * Set callback for video chunk processing
 *
 * @param dev Device to set callback for
 * @param cb Function pointer for processing video chunk
 */
public static native void freenect_set_video_chunk_callback(freenect_device dev, freenect_chunk_cb cb);

/**
 * Set the buffer to store depth information to. Size of buffer is
 * dependant on depth format. See FREENECT_DEPTH_*_SIZE defines for
 * more information.
 *
 * @param dev Device to set depth buffer for.
 * @param buf Buffer to store depth information to.
 *
 * @return 0 on success, < 0 on error
 */
public static native int freenect_set_depth_buffer(freenect_device dev, Pointer buf);

/**
 * Set the buffer to store depth information to. Size of buffer is
 * dependant on video format. See FREENECT_VIDEO_*_SIZE defines for
 * more information.
 *
 * @param dev Device to set video buffer for.
 * @param buf Buffer to store video information to.
 *
 * @return 0 on success, < 0 on error
 */
public static native int freenect_set_video_buffer(freenect_device dev, Pointer buf);

/**
 * Start the depth information stream for a device.
 *
 * @param dev Device to start depth information stream for.
 *
 * @return 0 on success, < 0 on error
 */
public static native int freenect_start_depth(freenect_device dev);

/**
 * Start the video information stream for a device.
 *
 * @param dev Device to start video information stream for.
 *
 * @return 0 on success, < 0 on error
 */
public static native int freenect_start_video(freenect_device dev);

/**
 * Stop the depth information stream for a device
 *
 * @param dev Device to stop depth information stream on.
 *
 * @return 0 on success, < 0 on error
 */
public static native int freenect_stop_depth(freenect_device dev);

/**
 * Stop the video information stream for a device
 *
 * @param dev Device to stop video information stream on.
 *
 * @return 0 on success, < 0 on error
 */
public static native int freenect_stop_video(freenect_device dev);

/**
 * Updates the accelerometer state using a blocking control message
 * call.
 *
 * @param dev Device to get accelerometer data from
 *
 * @return 0 on success, < 0 on error. Accelerometer data stored to
 * device struct.
 */
public static native int freenect_update_tilt_state(freenect_device dev);

/**
 * Retrieve the tilt state from a device
 *
 * @param dev Device to retrieve tilt state from
 *
 * @return The tilt state struct of the device
 */
public static native freenect_raw_tilt_state freenect_get_tilt_state(freenect_device dev);

/**
 * Return the tilt state, in degrees with respect to the horizon
 *
 * @param state The tilt state struct from a device
 *
 * @return Current degree of tilt of the device
 */
public static native double freenect_get_tilt_degs(freenect_raw_tilt_state state);

/**
 * Set the tilt state of the device, in degrees with respect to the
 * horizon. Uses blocking control message call to update
 * device. Function return does not reflect state of device, device
 * may still be moving to new position after the function returns. Use
 * freenect_get_tilt_status() to find current movement state.
 *
 * @param dev Device to set tilt state
 * @param angle Angle the device should tilt to
 *
 * @return 0 on success, < 0 on error.
 */
public static native int freenect_set_tilt_degs(freenect_device dev, double angle);

/**
 * Return the movement state of the tilt motor (moving, stopped, etc...)
 *
 * @param state Raw state struct to get the tilt status code from
 *
 * @return Status code of the tilt device. See
 * freenect_tilt_status_code enum for more info.
 */
public static native @Cast("freenect_tilt_status_code") int freenect_get_tilt_status(freenect_raw_tilt_state state);

/**
 * Set the state of the LED. Uses blocking control message call to
 * update device.
 *
 * @param dev Device to set the LED state
 * @param option LED state to set on device. See freenect_led_options enum.
 *
 * @return 0 on success, < 0 on error
 */
public static native int freenect_set_led(freenect_device dev, @Cast("freenect_led_options") int option);

/**
 * Get the axis-based gravity adjusted accelerometer state, as laid
 * out via the accelerometer data sheet, which is available at
 *
 * http://www.kionix.com/Product%20Sheets/KXSD9%20Product%20Brief.pdf
 *
 * @param state State to extract accelerometer data from
 * @param x Stores X-axis accelerometer state
 * @param y Stores Y-axis accelerometer state
 * @param z Stores Z-axis accelerometer state
 */
public static native void freenect_get_mks_accel(freenect_raw_tilt_state state, DoublePointer x, DoublePointer y, DoublePointer z);
public static native void freenect_get_mks_accel(freenect_raw_tilt_state state, DoubleBuffer x, DoubleBuffer y, DoubleBuffer z);
public static native void freenect_get_mks_accel(freenect_raw_tilt_state state, double[] x, double[] y, double[] z);

/**
 * Get the number of video camera modes supported by the driver.  This includes both RGB and IR modes.
 *
 * @return Number of video modes supported by the driver
 */
public static native int freenect_get_video_mode_count();

/**
 * Get the frame descriptor of the nth supported video mode for the
 * video camera.
 *
 * @param mode_num Which of the supported modes to return information about
 *
 * @return A freenect_frame_mode describing the nth video mode
 */
public static native @ByVal freenect_frame_mode freenect_get_video_mode(int mode_num);

/**
 * Get the frame descriptor of the current video mode for the specified
 * freenect device.
 *
 * @param dev Which device to return the currently-set video mode for
 *
 * @return A freenect_frame_mode describing the current video mode of the specified device
 */
public static native @ByVal freenect_frame_mode freenect_get_current_video_mode(freenect_device dev);

/**
 * Convenience function to return a mode descriptor matching the
 * specified resolution and video camera pixel format, if one exists.
 *
 * @param res Resolution desired
 * @param fmt Pixel format desired
 *
 * @return A freenect_frame_mode that matches the arguments specified, if such a valid mode exists; otherwise, an invalid freenect_frame_mode.
 */
public static native @ByVal freenect_frame_mode freenect_find_video_mode(@Cast("freenect_resolution") int res, @Cast("freenect_video_format") int fmt);

/**
 * Sets the current video mode for the specified device.  If the
 * freenect_frame_mode specified is not one provided by the driver
 * e.g. from freenect_get_video_mode() or freenect_find_video_mode()
 * then behavior is undefined.  The current video mode cannot be
 * changed while streaming is active.
 *
 * @param dev Device for which to set the video mode
 * @param mode Frame mode to set
 *
 * @return 0 on success, < 0 if error
 */
public static native int freenect_set_video_mode(freenect_device dev, @ByVal freenect_frame_mode mode);

/**
 * Get the number of depth camera modes supported by the driver.  This includes both RGB and IR modes.
 *
 * @return Number of depth modes supported by the driver
 */
public static native int freenect_get_depth_mode_count();

/**
 * Get the frame descriptor of the nth supported depth mode for the
 * depth camera.
 *
 * @param mode_num Which of the supported modes to return information about
 *
 * @return A freenect_frame_mode describing the nth depth mode
 */
public static native @ByVal freenect_frame_mode freenect_get_depth_mode(int mode_num);

/**
 * Get the frame descriptor of the current depth mode for the specified
 * freenect device.
 *
 * @param dev Which device to return the currently-set depth mode for
 *
 * @return A freenect_frame_mode describing the current depth mode of the specified device
 */
public static native @ByVal freenect_frame_mode freenect_get_current_depth_mode(freenect_device dev);

/**
 * Convenience function to return a mode descriptor matching the
 * specified resolution and depth camera pixel format, if one exists.
 *
 * @param res Resolution desired
 * @param fmt Pixel format desired
 *
 * @return A freenect_frame_mode that matches the arguments specified, if such a valid mode exists; otherwise, an invalid freenect_frame_mode.
 */
public static native @ByVal freenect_frame_mode freenect_find_depth_mode(@Cast("freenect_resolution") int res, @Cast("freenect_depth_format") int fmt);

/**
 * Sets the current depth mode for the specified device.  The mode
 * cannot be changed while streaming is active.
 *
 * @param dev Device for which to set the depth mode
 * @param mode Frame mode to set
 *
 * @return 0 on success, < 0 if error
 */
public static native int freenect_set_depth_mode(freenect_device dev, @Const @ByVal freenect_frame_mode mode);

/**
 * Enables or disables the specified flag.
 * 
 * @param flag Feature to set
 * @param value `FREENECT_OFF` or `FREENECT_ON`
 * 
 * @return 0 on success, < 0 if error
 */
public static native int freenect_set_flag(freenect_device dev, @Cast("freenect_flag") int flag, @Cast("freenect_flag_value") int value);


/**
 * Allows the user to specify a pointer to the audio firmware in memory for the Xbox 360 Kinect
 *
 * @param ctx Context to open device through
 * @param fw_ptr Pointer to audio firmware loaded in memory
 * @param num_bytes The size of the firmware in bytes
 */
public static native void freenect_set_fw_address_nui(freenect_context ctx, @Cast("unsigned char*") BytePointer fw_ptr, @Cast("unsigned int") int num_bytes);
public static native void freenect_set_fw_address_nui(freenect_context ctx, @Cast("unsigned char*") ByteBuffer fw_ptr, @Cast("unsigned int") int num_bytes);
public static native void freenect_set_fw_address_nui(freenect_context ctx, @Cast("unsigned char*") byte[] fw_ptr, @Cast("unsigned int") int num_bytes);

/**
 * Allows the user to specify a pointer to the audio firmware in memory for the K4W Kinect 
 *
 * @param ctx Context to open device through
 * @param fw_ptr Pointer to audio firmware loaded in memory
 * @param num_bytes The size of the firmware in bytes
 */
public static native void freenect_set_fw_address_k4w(freenect_context ctx, @Cast("unsigned char*") BytePointer fw_ptr, @Cast("unsigned int") int num_bytes);
public static native void freenect_set_fw_address_k4w(freenect_context ctx, @Cast("unsigned char*") ByteBuffer fw_ptr, @Cast("unsigned int") int num_bytes);
public static native void freenect_set_fw_address_k4w(freenect_context ctx, @Cast("unsigned char*") byte[] fw_ptr, @Cast("unsigned int") int num_bytes);


// #ifdef __cplusplus
// #endif


// Parsed from <libfreenect/libfreenect_registration.h>

/*
 * This file is part of the OpenKinect Project. http://www.openkinect.org
 *
 * Copyright (c) 2011 individual OpenKinect contributors. See the CONTRIB file
 * for details.
 *
 * This code is licensed to you under the terms of the Apache License, version
 * 2.0, or, at your option, the terms of the GNU General Public License,
 * version 2.0. See the APACHE20 and GPL2 files for the text of the licenses,
 * or the following URLs:
 * http://www.apache.org/licenses/LICENSE-2.0
 * http://www.gnu.org/licenses/gpl-2.0.txt
 *
 * If you redistribute this file in source form, modified or unmodified, you
 * may:
 *   1) Leave this header intact and distribute it under the same terms,
 *      accompanying it with the APACHE20 and GPL20 files, or
 *   2) Delete the Apache 2.0 clause and accompany it with the GPL2 file, or
 *   3) Delete the GPL v2 clause and accompany it with the APACHE20 file
 * In all cases you must keep the copyright notice intact and include a copy
 * of the CONTRIB file.
 *
 * Binary distributions must follow the binary distribution requirements of
 * either License.
 */
// #pragma once

// #include "libfreenect.h"
// #include <stdint.h>

// #ifdef __cplusplus
// #endif

/** Internal Kinect registration parameters.
 *  Structure matches that of the line protocol
 *  of the Kinect. */
public static class freenect_reg_info extends Pointer {
    static { Loader.load(); }
    public freenect_reg_info() { allocate(); }
    public freenect_reg_info(int size) { allocateArray(size); }
    public freenect_reg_info(Pointer p) { super(p); }
    private native void allocate();
    private native void allocateArray(int size);
    @Override public freenect_reg_info position(int position) {
        return (freenect_reg_info)super.position(position);
    }

	public native int dx_center(); public native freenect_reg_info dx_center(int dx_center); // not used by mapping algorithm

	public native int ax(); public native freenect_reg_info ax(int ax);
	public native int bx(); public native freenect_reg_info bx(int bx);
	public native int cx(); public native freenect_reg_info cx(int cx);
	public native int dx(); public native freenect_reg_info dx(int dx);

	public native int dx_start(); public native freenect_reg_info dx_start(int dx_start);

	public native int ay(); public native freenect_reg_info ay(int ay);
	public native int by(); public native freenect_reg_info by(int by);
	public native int cy(); public native freenect_reg_info cy(int cy);
	public native int dy(); public native freenect_reg_info dy(int dy);

	public native int dy_start(); public native freenect_reg_info dy_start(int dy_start);

	public native int dx_beta_start(); public native freenect_reg_info dx_beta_start(int dx_beta_start);
	public native int dy_beta_start(); public native freenect_reg_info dy_beta_start(int dy_beta_start);

	public native int rollout_blank(); public native freenect_reg_info rollout_blank(int rollout_blank); // not used by mapping algorithm
	public native int rollout_size(); public native freenect_reg_info rollout_size(int rollout_size);  // not used by mapping algorithm

	public native int dx_beta_inc(); public native freenect_reg_info dx_beta_inc(int dx_beta_inc);
	public native int dy_beta_inc(); public native freenect_reg_info dy_beta_inc(int dy_beta_inc);

	public native int dxdx_start(); public native freenect_reg_info dxdx_start(int dxdx_start);
	public native int dxdy_start(); public native freenect_reg_info dxdy_start(int dxdy_start);
	public native int dydx_start(); public native freenect_reg_info dydx_start(int dydx_start);
	public native int dydy_start(); public native freenect_reg_info dydy_start(int dydy_start);

	public native int dxdxdx_start(); public native freenect_reg_info dxdxdx_start(int dxdxdx_start);
	public native int dydxdx_start(); public native freenect_reg_info dydxdx_start(int dydxdx_start);
	public native int dxdxdy_start(); public native freenect_reg_info dxdxdy_start(int dxdxdy_start);
	public native int dydxdy_start(); public native freenect_reg_info dydxdy_start(int dydxdy_start);

	public native int back_comp1(); public native freenect_reg_info back_comp1(int back_comp1); // not used by mapping algorithm

	public native int dydydx_start(); public native freenect_reg_info dydydx_start(int dydydx_start);

	public native int back_comp2(); public native freenect_reg_info back_comp2(int back_comp2); // not used by mapping algorithm

	public native int dydydy_start(); public native freenect_reg_info dydydy_start(int dydydy_start);
}

/** registration padding info (?) */
public static class freenect_reg_pad_info extends Pointer {
    static { Loader.load(); }
    public freenect_reg_pad_info() { allocate(); }
    public freenect_reg_pad_info(int size) { allocateArray(size); }
    public freenect_reg_pad_info(Pointer p) { super(p); }
    private native void allocate();
    private native void allocateArray(int size);
    @Override public freenect_reg_pad_info position(int position) {
        return (freenect_reg_pad_info)super.position(position);
    }

	public native @Cast("uint16_t") short start_lines(); public native freenect_reg_pad_info start_lines(short start_lines);
	public native @Cast("uint16_t") short end_lines(); public native freenect_reg_pad_info end_lines(short end_lines);
	public native @Cast("uint16_t") short cropping_lines(); public native freenect_reg_pad_info cropping_lines(short cropping_lines);
}

/** internal Kinect zero plane data */
public static class freenect_zero_plane_info extends Pointer {
    static { Loader.load(); }
    public freenect_zero_plane_info() { allocate(); }
    public freenect_zero_plane_info(int size) { allocateArray(size); }
    public freenect_zero_plane_info(Pointer p) { super(p); }
    private native void allocate();
    private native void allocateArray(int size);
    @Override public freenect_zero_plane_info position(int position) {
        return (freenect_zero_plane_info)super.position(position);
    }

	public native float dcmos_emitter_dist(); public native freenect_zero_plane_info dcmos_emitter_dist(float dcmos_emitter_dist);    // Distance between IR camera and IR emitter, in cm.
	public native float dcmos_rcmos_dist(); public native freenect_zero_plane_info dcmos_rcmos_dist(float dcmos_rcmos_dist);      // Distance between IR camera and RGB camera, in cm.
	public native float reference_distance(); public native freenect_zero_plane_info reference_distance(float reference_distance);    // The focal length of the IR camera, in mm.
	public native float reference_pixel_size(); public native freenect_zero_plane_info reference_pixel_size(float reference_pixel_size);  // The size of a single pixel on the zero plane, in mm.
}

/** all data needed for depth->RGB mapping */
public static class freenect_registration extends Pointer {
    static { Loader.load(); }
    public freenect_registration() { allocate(); }
    public freenect_registration(int size) { allocateArray(size); }
    public freenect_registration(Pointer p) { super(p); }
    private native void allocate();
    private native void allocateArray(int size);
    @Override public freenect_registration position(int position) {
        return (freenect_registration)super.position(position);
    }

	public native @ByRef freenect_reg_info reg_info(); public native freenect_registration reg_info(freenect_reg_info reg_info);
	public native @ByRef freenect_reg_pad_info reg_pad_info(); public native freenect_registration reg_pad_info(freenect_reg_pad_info reg_pad_info);
	public native @ByRef freenect_zero_plane_info zero_plane_info(); public native freenect_registration zero_plane_info(freenect_zero_plane_info zero_plane_info);

	public native double const_shift(); public native freenect_registration const_shift(double const_shift);

	public native @Cast("uint16_t*") ShortPointer raw_to_mm_shift(); public native freenect_registration raw_to_mm_shift(ShortPointer raw_to_mm_shift);
	public native IntPointer depth_to_rgb_shift(); public native freenect_registration depth_to_rgb_shift(IntPointer depth_to_rgb_shift);
	public native int registration_table(int i, int j); public native freenect_registration registration_table(int i, int j, int registration_table);
	@MemberGetter public native @Cast("int32_t*") IntPointer registration_table();  // A table of 640*480 pairs of x,y values.
	                                   // Index first by pixel, then x:0 and y:1.
}


// These allow clients to export registration parameters; proper docs will
// come later
public static native @ByVal freenect_registration freenect_copy_registration(freenect_device dev);
public static native int freenect_destroy_registration(freenect_registration reg);

// convenience function to convert a single x-y coordinate pair from camera
// to world coordinates
public static native void freenect_camera_to_world(freenect_device dev,
	int cx, int cy, int wz, DoublePointer wx, DoublePointer wy);
public static native void freenect_camera_to_world(freenect_device dev,
	int cx, int cy, int wz, DoubleBuffer wx, DoubleBuffer wy);
public static native void freenect_camera_to_world(freenect_device dev,
	int cx, int cy, int wz, double[] wx, double[] wy);

// #ifdef __cplusplus
// #endif


// Parsed from <libfreenect/libfreenect_audio.h>

/*
 * This file is part of the OpenKinect Project. http://www.openkinect.org
 *
 * Copyright (c) 2011 individual OpenKinect contributors. See the CONTRIB file
 * for details.
 *
 * This code is licensed to you under the terms of the Apache License, version
 * 2.0, or, at your option, the terms of the GNU General Public License,
 * version 2.0. See the APACHE20 and GPL2 files for the text of the licenses,
 * or the following URLs:
 * http://www.apache.org/licenses/LICENSE-2.0
 * http://www.gnu.org/licenses/gpl-2.0.txt
 *
 * If you redistribute this file in source form, modified or unmodified, you
 * may:
 *   1) Leave this header intact and distribute it under the same terms,
 *      accompanying it with the APACHE20 and GPL20 files, or
 *   2) Delete the Apache 2.0 clause and accompany it with the GPL2 file, or
 *   3) Delete the GPL v2 clause and accompany it with the APACHE20 file
 * In all cases you must keep the copyright notice intact and include a copy
 * of the CONTRIB file.
 *
 * Binary distributions must follow the binary distribution requirements of
 * either License.
 */
// #pragma once

// #include "libfreenect.h"
// #include <stdint.h>

// #ifdef __cplusplus
// #endif

/** Structure to represent a single 16-bit signed little-endian PCM sample. */
public static class freenect_sample_51 extends Pointer {
    static { Loader.load(); }
    public freenect_sample_51() { allocate(); }
    public freenect_sample_51(int size) { allocateArray(size); }
    public freenect_sample_51(Pointer p) { super(p); }
    private native void allocate();
    private native void allocateArray(int size);
    @Override public freenect_sample_51 position(int position) {
        return (freenect_sample_51)super.position(position);
    }

	public native short left(); public native freenect_sample_51 left(short left);
	public native short right(); public native freenect_sample_51 right(short right);
	public native short center(); public native freenect_sample_51 center(short center);
	public native short lfe(); public native freenect_sample_51 lfe(short lfe);
	public native short surround_left(); public native freenect_sample_51 surround_left(short surround_left);
	public native short surround_right(); public native freenect_sample_51 surround_right(short surround_right);
}

/**
 * Typedef for "you wanted this microphone data, here it is" event callbacks.
 * TODO: Timestamp details
 * The format of the unknown stream is as of yet undetermined.
 *
 * @param dev Device which triggered this callback
 * @param num_samples Number of samples provided in each of the audio data arrays (mic[1-4] and cancelled)
 * @param mic1 Microphone data for the leftmost microphone: 32-bit PCM little-endian samples at 16kHz.
 * @param mic2 Microphone data for the left-middle microphone: 32-bit PCM little-endian samples at 16kHz.
 * @param mic3 Microphone data for the right-middle microphone: 32-bit PCM little-endian samples at 16kHz.
 * @param mic4 Microphone data for the rightmost microphone: 32-bit PCM little-endian samples at 16kHz.
 * @param cancelled Noise-cancelled audio data: 16-bit PCM little-endian samples at 16kHz.
 */
public static class freenect_audio_in_cb extends FunctionPointer {
    static { Loader.load(); }
    public    freenect_audio_in_cb(Pointer p) { super(p); }
    protected freenect_audio_in_cb() { allocate(); }
    private native void allocate();
    public native void call(freenect_device dev, int num_samples,
                                     IntPointer mic1, IntPointer mic2,
                                     IntPointer mic3, IntPointer mic4,
                                     ShortPointer cancelled, Pointer unknown);
}

/**
 * Typedef for "you're playing audio, the library needs you to fill up the outgoing audio buffer" event callbacks
 * The library will request samples at a rate of 48000Hz.
 *
 * @param dev Device this callback was triggered for
 * @param samples Pointer to the memory where the library expects you to copy the next sample_count freenect_sample_51's to.
 * @param sample_count Bidirectional. in: maximum number of samples the driver wants (don't copy in more than this, you'll clobber memory).  out: actual number of samples provided to the driver.
 */
public static class freenect_audio_out_cb extends FunctionPointer {
    static { Loader.load(); }
    public    freenect_audio_out_cb(Pointer p) { super(p); }
    protected freenect_audio_out_cb() { allocate(); }
    private native void allocate();
    public native void call(freenect_device dev, freenect_sample_51 samples, IntPointer sample_count);
}

/**
 * Set the audio in callback.  This is the function called when the library
 * has new microphone samples.  It will be called approximately 62.5 times per
 * second (16kHz sample rate, expect 512 samples/callback)
 *
 * @param dev Device for which to set the callback
 * @param callback Callback function to set
 */
public static native void freenect_set_audio_in_callback(freenect_device dev, freenect_audio_in_cb callback);

/**
 * Set the audio out callback.  This is the "tell me what audio you're about
 * to play through the speakers so the Kinect can subtract it out" callback for
 * a given device.  If you choose not set an audio_out_callback, the library
 * will send silence to the Kinect for you - it requires data either way.
 *
 * @param dev Device for which to set the callback
 * @param callback Callback function to set
 */
public static native void freenect_set_audio_out_callback(freenect_device dev, freenect_audio_out_cb callback);

/**
 * Start streaming audio for the specified device.
 *
 * @param dev Device for which to start audio streaming
 *
 * @return 0 on success, < 0 if error
 */
public static native int freenect_start_audio(freenect_device dev);

/**
 * Stop streaming audio for the specified device.
 *
 * @param dev Device for which to stop audio streaming
 *
 * @return 0 on success, < 0 if error
 */
public static native int freenect_stop_audio(freenect_device dev);

// #ifdef __cplusplus
// #endif


// Parsed from <libfreenect/libfreenect_sync.h>

/*
 * This file is part of the OpenKinect Project. http://www.openkinect.org
 *
 * Copyright (c) 2010 Brandyn White (bwhite@dappervision.com)
 *                    Andrew Miller (amiller@dappervision.com)
 *
 * This code is licensed to you under the terms of the Apache License, version
 * 2.0, or, at your option, the terms of the GNU General Public License,
 * version 2.0. See the APACHE20 and GPL2 files for the text of the licenses,
 * or the following URLs:
 * http://www.apache.org/licenses/LICENSE-2.0
 * http://www.gnu.org/licenses/gpl-2.0.txt
 *
 * If you redistribute this file in source form, modified or unmodified, you
 * may:
 *   1) Leave this header intact and distribute it under the same terms,
 *      accompanying it with the APACHE20 and GPL20 files, or
 *   2) Delete the Apache 2.0 clause and accompany it with the GPL2 file, or
 *   3) Delete the GPL v2 clause and accompany it with the APACHE20 file
 * In all cases you must keep the copyright notice intact and include a copy
 * of the CONTRIB file.
 *
 * Binary distributions must follow the binary distribution requirements of
 * either License.
 */
// #pragma once

// #include "libfreenect.h"
// #include <stdint.h>


/** If Win32, export all functions for DLL usage */
// #ifndef _WIN32
/** DLLExport information for windows, set to nothing on other platforms */
/** DLLExport information for windows, set to nothing on other platforms */
//   #define FREENECTAPI_SYNC
// #else
//   #ifdef __cplusplus
//     #define FREENECTAPI_SYNC extern "C" __declspec(dllexport)
//   #else
    // this is required when building from a Win32 port of gcc without being
    // forced to compile all of the library files (.c) with g++...
//     #define FREENECTAPI_SYNC __declspec(dllexport)
//   #endif
// #endif

// #ifdef __cplusplus
// #endif

public static native int freenect_sync_get_video_with_res(@Cast("void**") PointerPointer video, @Cast("uint32_t*") IntPointer timestamp, int index,
        @Cast("freenect_resolution") int res, @Cast("freenect_video_format") int fmt);
public static native int freenect_sync_get_video_with_res(@Cast("void**") @ByPtrPtr Pointer video, @Cast("uint32_t*") IntPointer timestamp, int index,
        @Cast("freenect_resolution") int res, @Cast("freenect_video_format") int fmt);
public static native int freenect_sync_get_video_with_res(@Cast("void**") @ByPtrPtr Pointer video, @Cast("uint32_t*") IntBuffer timestamp, int index,
        @Cast("freenect_resolution") int res, @Cast("freenect_video_format") int fmt);
public static native int freenect_sync_get_video_with_res(@Cast("void**") @ByPtrPtr Pointer video, @Cast("uint32_t*") int[] timestamp, int index,
        @Cast("freenect_resolution") int res, @Cast("freenect_video_format") int fmt);
/*  Synchronous video function, starts the runloop if it isn't running

    The returned buffer is valid until this function is called again, after which the buffer must not
    be used again.  Make a copy if the data is required.

    Args:
        video: Populated with a pointer to a video buffer with a size of the requested type
        timestamp: Populated with the associated timestamp
        index: Device index (0 is the first)
        res: Valid resolution
        fmt: Valid format

    Returns:
        Nonzero on error.
*/

public static native int freenect_sync_get_video(@Cast("void**") PointerPointer video, @Cast("uint32_t*") IntPointer timestamp, int index, @Cast("freenect_video_format") int fmt);
public static native int freenect_sync_get_video(@Cast("void**") @ByPtrPtr Pointer video, @Cast("uint32_t*") IntPointer timestamp, int index, @Cast("freenect_video_format") int fmt);
public static native int freenect_sync_get_video(@Cast("void**") @ByPtrPtr Pointer video, @Cast("uint32_t*") IntBuffer timestamp, int index, @Cast("freenect_video_format") int fmt);
public static native int freenect_sync_get_video(@Cast("void**") @ByPtrPtr Pointer video, @Cast("uint32_t*") int[] timestamp, int index, @Cast("freenect_video_format") int fmt);
/*  Does the exact same as above, but with a default resolution,
    so backwards compatibilty is maintained.
    The Resolution is kept at the default FREENECT_RESOLUTION_MEDIUM
*/

public static native int freenect_sync_get_depth_with_res(@Cast("void**") PointerPointer depth, @Cast("uint32_t*") IntPointer timestamp, int index,
        @Cast("freenect_resolution") int res, @Cast("freenect_depth_format") int fmt);
public static native int freenect_sync_get_depth_with_res(@Cast("void**") @ByPtrPtr Pointer depth, @Cast("uint32_t*") IntPointer timestamp, int index,
        @Cast("freenect_resolution") int res, @Cast("freenect_depth_format") int fmt);
public static native int freenect_sync_get_depth_with_res(@Cast("void**") @ByPtrPtr Pointer depth, @Cast("uint32_t*") IntBuffer timestamp, int index,
        @Cast("freenect_resolution") int res, @Cast("freenect_depth_format") int fmt);
public static native int freenect_sync_get_depth_with_res(@Cast("void**") @ByPtrPtr Pointer depth, @Cast("uint32_t*") int[] timestamp, int index,
        @Cast("freenect_resolution") int res, @Cast("freenect_depth_format") int fmt);
/*  Synchronous depth function, starts the runloop if it isn't running

    The returned buffer is valid until this function is called again, after which the buffer must not
    be used again.  Make a copy if the data is required.

    Args:
        depth: Populated with a pointer to a depth buffer with a size of the requested type
        timestamp: Populated with the associated timestamp
        index: Device index (0 is the first)
        res: Valid resolution
        fmt: Valid format

    Returns:
        Nonzero on error.
*/

public static native int freenect_sync_get_depth(@Cast("void**") PointerPointer depth, @Cast("uint32_t*") IntPointer timestamp, int index, @Cast("freenect_depth_format") int fmt);
public static native int freenect_sync_get_depth(@Cast("void**") @ByPtrPtr Pointer depth, @Cast("uint32_t*") IntPointer timestamp, int index, @Cast("freenect_depth_format") int fmt);
public static native int freenect_sync_get_depth(@Cast("void**") @ByPtrPtr Pointer depth, @Cast("uint32_t*") IntBuffer timestamp, int index, @Cast("freenect_depth_format") int fmt);
public static native int freenect_sync_get_depth(@Cast("void**") @ByPtrPtr Pointer depth, @Cast("uint32_t*") int[] timestamp, int index, @Cast("freenect_depth_format") int fmt);
/*  Again, a wrapper function to keep backward compatibility.
    The Resolution is kept at the default FREENECT_RESOLUTION_MEDIUM

*/

public static native int freenect_sync_set_tilt_degs(int angle, int index);
/*  Tilt function, starts the runloop if it isn't running

    Args:
        angle: Set the angle to tilt the device
		    index: Device index (0 is the first)

    Returns:
        Nonzero on error.
*/

public static native int freenect_sync_get_tilt_state(@Cast("freenect_raw_tilt_state**") PointerPointer state, int index);
public static native int freenect_sync_get_tilt_state(@ByPtrPtr freenect_raw_tilt_state state, int index);
/*  Tilt state function, starts the runloop if it isn't running

    Args:
        state: Populated with an updated tilt state pointer
		    index: Device index (0 is the first)

    Returns:
        Nonzero on error.
*/

public static native int freenect_sync_set_led(@Cast("freenect_led_options") int led, int index);
/*  Led function, starts the runloop if it isn't running

    Args:
        led: The LED state to set the device to
		    index: Device index (0 is the first)

    Returns:
        Nonzero on error.
*/

public static native int freenect_sync_camera_to_world(int cx, int cy, int wz, DoublePointer wx, DoublePointer wy, int index);
public static native int freenect_sync_camera_to_world(int cx, int cy, int wz, DoubleBuffer wx, DoubleBuffer wy, int index);
public static native int freenect_sync_camera_to_world(int cx, int cy, int wz, double[] wx, double[] wy, int index);
/*  Camera to world mapping, starts the runloop if it isn't running

    Wraps libfreenect_registration.h function of same name.
*/

public static native void freenect_sync_stop();
// #ifdef __cplusplus
// #endif


}
