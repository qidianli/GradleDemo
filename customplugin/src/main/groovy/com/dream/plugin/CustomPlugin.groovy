package com.dream.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project


class CustomPlugin implements Plugin<Project>{

    @Override
    void apply(Project project) {
        println 'Hello, I am CustomPlugin'

        //自定义 Task
        def customTask = project.tasks.create('CustomTask'){
            doLast {
                println 'Hello, I am CustomTask....'
            }
        }

        //挂接到 mergeDebugResources 后面执行
        project.afterEvaluate {
            //获取 Task 时，需在当前 build.gradle 文件执行之后，否则获取的 Task 会为 null
            //获取构建流程中的 Task
            def mergeDebugResources = project.tasks.findByName("mergeDebugResources")
            if(mergeDebugResources != null){
                mergeDebugResources.finalizedBy(customTask)
            }
        }
    }
}