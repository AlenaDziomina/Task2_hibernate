package com.epam.testapp.database.dao.jdbc;

import java.util.List;

public interface QueryMapper <T> {
    <T> StringBuilder mapQuery(List<T> objList, List params);
}
