/*!
* Copyright 2010 - 2013 Pentaho Corporation.  All rights reserved.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*
*/

package org.apache.hadoop.hive.ql.udf;

import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDFUtils;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;

/**
 * UDFLength.
 *
 */
@Description(name = "length",
    value = "_FUNC_(str) - Returns the length of str ",
    extended = "Example:\n"
    + "  > SELECT _FUNC_('Facebook') FROM src LIMIT 1;\n" + "  8")
public class UDFLength extends UDF {
  private final IntWritable result = new IntWritable();

  public IntWritable evaluate(Text s) {
    if (s == null) {
      return null;
    }

    byte[] data = s.getBytes();
    int len = 0;
    for (int i = 0; i < s.getLength(); i++) {
      if (GenericUDFUtils.isUtfStartByte(data[i])) {
        len++;
      }
    }

    result.set(len);
    return result;
  }
}
