package com.sq.filter;

import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;

public class MyTypeFilter implements TypeFilter {
    @Override
    public boolean match(MetadataReader metadataReader,
                         MetadataReaderFactory metadataReaderFactory)
            throws IOException {

        String name = metadataReader.getClassMetadata().getClassName();
        return name.matches(".*My.*");
    }
}
