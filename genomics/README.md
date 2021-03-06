### Genomics
This folder contains implementations of genomics related tools.
All of the tools uses [Apache Spark](http://spark.apache.org/) to distribute
the workload.

Currently all of the tools does this by dividing the input into smaller chunks,
and then running the tools on each chunk. By doing so we are able to provide
a familiar interface, while (in most cases) improving the performance of each
tool.

### Tools
* **SparkBWA** is a short read aligner which uses
  [BWA](http://bio-bwa.sourceforge.net/) and Spark to
  perform alignment in a distributed manner.
* **SparkCaller** is used both preprocesses the file obtained from
  SparkBWA and does variant discovery. It uses the [GATK
  toolkit](https://www.google.no/search?q=GATK+bqsr&oq=GATK&aqs=chrome.0.69i59j69i57j69i60l4.431j0j1&sourceid=chrome&ie=UTF-8#safe=off&q=GATK+) to perform the
  analysis.
* **Pipeline runner** is a tool for running SparkBWA and SparkCaller as
  a single pipeline in a standalone JAR file.

### Requirements
* Indexed FASTA reference files (as normally required in the GATK pipeline).
* dbSNP files for the reference (the one for HG19 can be found by following the
  this [link](https://software.broadinstitute.org/gatk/download/bundle).
* It is preferred that NFS is used over HDFS, as GATK seemingly has some
  problem when writing to HDFS.

![GATK Workflow](img/spark_bio_workflow.png "Parts of the GATK workflow implemented
using Spark")

### Benchmarks
The large dataset is the Illumina 100bp pair-ended exome 150x GCAT dataset.
small dataset refers to the Ion Torrent 225bp single-ended exome 30x GCAT
dataset. These datasets can be obtained
[here](https://f.128.no/gcat/).

Each data point is an average of three runs using the same settings.


![SparkBWA small](img/bwa_small.png "The runtime of the BWA and SparkBWA
on the small dataset")

![SparkBWA large](img/bwa_large.png "The runtime of the BWA and SparkBWA
on the large dataset")

![Whole pipeline small](img/whole_pipeline_small.png "The runtime of the whole
pipeline on the small dataset")

![Whole pipeline large](img/whole_pipeline_large.png "The runtime of the whole
pipeline on the large dataset")
