#! /bin/bash
# index_file.sh
# Import a single marc file into a Solr index
# $Id: indexfile.sh 

E_BADARGS=65

if [ $# -eq 0 ]
then
  echo "    Usage: `basename $0` [config.properties] ./path/to/marc.mrc [./path/to/ids_to_delete.del]"
  echo "      Note that if the config.properties file is not specified the Jarfile will be searched for"
  echo "      a file whose name ends with \"config.properties\""
  exit $E_BADARGS
fi

java @MEM_ARGS@ -jar @CUSTOM_JAR_NAME@ $1 $2 $3

exit 0

