module.exports = {
    presets: [
        [
            '@babel/preset-env',
            {
                modules: false,
                useBuiltIns: 'usage',
                targets: 'cover 99.5% in KR',
                corejs: {
                    version: 2,
                    proposals: true
                }
            }
        ]
    ]
};