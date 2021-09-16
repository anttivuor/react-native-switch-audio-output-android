# react-native-switch-audio-output-android
Library for selecting audio output source (**only for Android**). Uses Android's AudioManager class.

## Getting started

`npm install react-native-switch-audio-output-android`
or
`yarn add react-native-switch-audio-output-android`

### If you are using React Native < 0.60, link React Native dependency and install CocoaPods dependency

`$ react-native link react-native-switch-audio-output && cd ios && pod install && cd ..`

## Usage
```javascript
import AudioOutput from 'react-native-switch-audio-output-android';

// Get list of possible devices
const devices = await AudioOutput.getAudioDevices();
// Returns a list of possible devices
// ["Speaker", "Bluetooth", "Headphones"]

// Switch to desired device
AudioOutput.setAudioDevice("Bluetooth");
// Note: the device must be one of the values that getAudioDevices() returned
```