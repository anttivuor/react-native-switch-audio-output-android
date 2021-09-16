declare type audioOutput = {
    getAudioDevices: () => string[],
    setAudioDevice: (deviceName: string) => boolean,
};

export = audioOutput;