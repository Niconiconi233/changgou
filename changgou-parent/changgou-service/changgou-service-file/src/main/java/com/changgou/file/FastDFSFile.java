package com.changgou.file;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @program: changgou
 * @description:
 * @author: YW
 * @create: 2021-04-12 22:44
 **/
@Getter
@Setter
public class FastDFSFile implements Serializable {

    private static final long serialVersionUID = 7200714564583103418L;

    private String name;

    private byte[] content;

    private String ext;

    private String md5;

    private String author;

    public FastDFSFile(String name, byte[] content, String ext, String md5, String author) {
        this.name = name;
        this.content = content;
        this.ext = ext;
        this.md5 = md5;
        this.author = author;
    }

    public FastDFSFile(String name, byte[] content, String ext) {
        this.name = name;
        this.content = content;
        this.ext = ext;
    }

    public FastDFSFile() {}

}
