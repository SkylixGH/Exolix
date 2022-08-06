cmake_minimum_required(VERSION 3.0)

project(SomeC)

add_library(SomeC SHARED gr.cxx)
target_include_directories(SomeC PUBLIC include)
