# account-service
Simple spring boot project testing  account service in REST

**update 31.12.2017**

Added oAuth2.0 support with PASSWORD grant type.

This is how to get access token:
`curl -X POST --user "client-api:secret" -d "grant_type=password&username=admin@example.com&password=keymaster" http://localhost:8080/oauth/token`

This is how to execute request to resource:
`curl -i -H "Accept: application/json" -X GET http://localhost:8080/api/account/all?access_token=$ACCESS_TOKEN
`




