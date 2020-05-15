require 'capybara/cucumber'
require 'selenium-webdriver'
require 'pry'
require 'json'
Capybara.configure do |config|
    #selenium   selenium_chrome selenioum_chrome_headless
    # config.default_driver = :selenium_chrome_headless
    config.default_driver = :selenium_chrome
    config.app_host = 'http://www.cmmc.com.br/'
    config.default_max_wait_time = 5
end
