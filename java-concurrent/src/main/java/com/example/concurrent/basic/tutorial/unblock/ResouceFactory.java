package com.example.concurrent.basic.tutorial.unblock;

class ResouceFactory {
  private static class ResouceHolder {
    private static Resource resource = new Resource();
  }
  private static class Resource {
  }

  public Resource getInstance() {
    return ResouceHolder.resource;
  }
}
