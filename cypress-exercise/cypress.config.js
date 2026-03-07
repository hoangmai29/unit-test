const { defineConfig } = require('cypress');

module.exports = defineConfig({
  e2e: {
    baseUrl: 'https://www.saucedemo.com',
    supportFile: false,
    // Cấu hình viewport
    viewportWidth: 1280,
    viewportHeight: 720,
    // Timeout settings
    defaultCommandTimeout: 10000,
    pageLoadTimeout: 30000,
    // Video recording
    video: true,
    // Screenshot on failure
    screenshotOnRunFailure: true,
  },
});
