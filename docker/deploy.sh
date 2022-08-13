#!/bin/sh
EXIST_BLUE=$(docker-compose ps | grep blue | grep Up)

if [ -z "$EXIST_BLUE" ]; then
  docker-compose pull checkmoi-green
  docker-compose up -d checkmoi-green

  sleep 10
  docker-compose stop checkmoi-blue
else
  docker-compose pull checkmoi-blue
  docker-compose up -d checkmoi-blue

  sleep 10
  docker-compose stop checkmoi-green
fi