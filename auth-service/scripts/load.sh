#!/bin/bash
#
# Copyright (c) METAMAGIC GLOBAL INC, USA
#
# This script will load the data in associated database

FILE_PATH=$(pwd)
POSTGRES=/usr/lib/postgresql/9.6/bin
DBNAME=postgres
DBUSER=postgres

echo Load database process started.

echo Loading default trasactions data.
$POSTGRES/psql -d $DBNAME -U $DBUSER -c "\COPY \"LOGIN\" from '$FILE_PATH/data/LOGIN.csv' with delimiter '#' CSV HEADER"
echo Loading trasactions data successfully done.

echo Load database process successfully completed.