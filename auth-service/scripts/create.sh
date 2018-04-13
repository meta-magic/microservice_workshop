#!/bin/bash
#
# Copyright (c) METAMAGIC GLOBAL INC, USA
#
# This script will create the database setup
#

FILE_PATH=$(pwd)
POSTGRES=/usr/lib/postgresql/9.6/bin
DBNAME=postgres
DBUSER=postgres

echo Creating transactional tables.
$POSTGRES/psql -d $DBNAME -U $DBUSER -f $FILE_PATH/create/LOGIN.sql
echo Transactional tables created successfully.