![](https://github.com/Tyro-sc/tyro/workflows/Chrome%20Tests/badge.svg)
![](https://github.com/Tyro-sc/tyro/workflows/Firefox%20Tests/badge.svg)

[![Maintainability](https://api.codeclimate.com/v1/badges/c091b16c5832c890a59b/maintainability)](https://codeclimate.com/github/Tyro-sc/tyro/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/c091b16c5832c890a59b/test_coverage)](https://codeclimate.com/github/Tyro-sc/tyro/test_coverage)

# Tyro

## Api
 -[ ] Factory methods - DA
 -[ ] Transverse API - DA

## Documentation
 -[ ] Review Overview - DA
 -[ ] Architecture Schema - DA
 -[ ] Project getting started - DA
 -[ ] 1 template with only Tyro - DA
 -[ ] 1 template with Tyro + cucumber - DA
 -[ ] 1 template with docker containers - DA
 -[ ] 1 template with Saucelab, Browser-stack ... - DA
 
### First part (Core)
 -[ ] First part of doc oriented on tyro-core
 -[ ] Core domain (separate by themes) + recapture screenshot with good css
 -[ ] Core DSL
 -[ ] Core interaction
 -[ ] Core intention

### Second part (Web)
 -[ ] Web Bundle
 -[ ] html5-core equivalence
 -[ ] Factories Pattern
 -[ ] $ et $$ + css3 selector
 -[ ] selector shortcut (button('Ok'))
 -[ ] Transverse API - DA
 -[ ] How to create a custom component - DA
 -[ ] How to share a custom component (in the road of Design System) - DA
  
### Third part (Native)

### Appendices
 -[ ] Table component/state/properties
 -[ ] Community

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