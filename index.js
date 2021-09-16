/**
 * @providesModule RNSwitchAudioOutput
 */

var {NativeModules} = require("react-native");
var RNSwitchAudioOutput = NativeModules.RNSwitchAudioOutput || {};

var audioOutput = {
    getAudioDevices: () => RNSwitchAudioOutput.getAudioDevices(),
    setAudioDevice: (deviceName) => RNSwitchAudioOutput.setAudioDevice(deviceName),
};

module.exports = audioOutput;