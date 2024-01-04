package com.ebay.epic.sojourner.flink.parser;

public interface Parser<Source, Target> {

  void init() throws Exception;

  void parse(Source source, Target target) throws Exception;
}
