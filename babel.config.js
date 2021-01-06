module.exports = {
    presets: [
        [
            '@babel/preset-env',
            {
                modules: false,
                useBuiltIns: 'usage',
                targets: 'cover 99.5% in KR',
                corejs: {
                    version: 3,
                    proposals: true
                }
            }
        ]
    ]
};