![](https://github.com/Tyro-sc/tyro/workflows/Core%20Tests/badge.svg)
![](https://github.com/Tyro-sc/tyro/workflows/Chrome%20Web%20Bundle%20Tests/badge.svg)
![](https://github.com/Tyro-sc/tyro/workflows/Firefox%20Web%20Bundle%20Tests/badge.svg)

[![Maintainability](https://api.codeclimate.com/v1/badges/c091b16c5832c890a59b/maintainability)](https://codeclimate.com/github/Tyro-sc/tyro/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/c091b16c5832c890a59b/test_coverage)](https://codeclimate.com/github/Tyro-sc/tyro/test_coverage)

# tyro

DOC:
ClickOn Tyro base + Mouse => 95% use case
Collection of Key + click => groovy extension
If custom low level use Config.provider

Wait:
 if change time out don't forget to change it at this end of test
 
Registered script added for all the Selenium Provider session so re added to each refresh, visit ...

datetime-local instead of type="datetime" see https://developer.mozilla.org/fr/docs/Web/HTML/Element/Input/datetime
 
 
 On switch window don't forget ton switch to old windows (page never closed) or add close all windows in afterAll
 
 
docker run --rm -ti --name zalenium -p 4444:4444 -v /var/run/docker.sock:/var/run/docker.sock -v /tmp/videos:/home/david/Videos --privileged dosel/zalenium start