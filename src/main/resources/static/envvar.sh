#!/bin/bash

echo "Setting up environment variables"
export DB_URL="jdbc:mysql://psugvsecapstone-psugvsecapstone.f.aivencloud.com:26571"
export DB_USERNAME="capstone"
export DB_PASSWORD="capstone"

echo "DB_URL=$DB_URL" >> $GITHUB_ENV
echo "DB_USERNAME=$DB_USERNAME" >> $GITHUB_ENV
echo "DB_PASSWORD=$DB_PASSWORD" >> $GITHUB_ENV

echo "Environment variables are set!"