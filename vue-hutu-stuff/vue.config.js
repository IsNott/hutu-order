const { defineConfig } = require('@vue/cli-service')
const path = require('path')
const defaultSettings = require('./src/settings.js')

function resolve(dir) {
  return path.join(__dirname, dir)
}

const name = defaultSettings.title || 'vue Hutu Stuff' // page title


module.exports = defineConfig({
  lintOnSave: false,
  transpileDependencies: true,
  configureWebpack: {
    externals: {
      'electron': 'require("electron")',
    },
    name: name,
    resolve: {
      alias: {
        '@': resolve('src')
      }
    }
  },
  pluginOptions: {
    electronBuilder: {
      builderOptions: {
        directories: {
          output: 'dist_electron'
        },
        files: [
          '**/*'
        ],
        extraResources: [
          {
            from: 'src/static',
            to: 'static',
            filter: ['**/*']
          }
        ],
      }
    }
  }
})
