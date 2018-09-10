#!/usr/bin/env bash
curl https://api.stripe.com/v1/charges?limit=25 -u sk_test_4eC39HqLyjWDarjtT1zdp7dc: | jq