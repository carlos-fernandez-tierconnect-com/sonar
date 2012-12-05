/*
 * Sonar, open source software quality management tool.
 * Copyright (C) 2008-2012 SonarSource
 * mailto:contact AT sonarsource DOT com
 *
 * Sonar is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * Sonar is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with Sonar; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02
 */
package org.sonar.batch.bootstrap;

import com.google.common.annotations.VisibleForTesting;
import org.apache.commons.lang.StringUtils;

import java.text.MessageFormat;

public class DurationLabel {

  private String suffixAgo = "ago";
  private String seconds = "less than a minute";
  private String minute = "about a minute";
  private String minutes = "{0} minutes";
  private String hour = "about an hour";
  private String hours = "{0} hours";
  private String day = "a day";
  private String days = "{0} days";
  private String month = "about a month";
  private String months = "{0} months";
  private String year = "about a year";
  private String years = "{0} years";

  public String label(long durationInMillis) {
    double nbSeconds = durationInMillis / 1000.0;
    double nbMinutes = nbSeconds / 60;
    double nbHours = nbMinutes / 60;
    double nbDays = nbHours / 24;
    double nbYears = nbDays / 365;

    String time = MessageFormat.format(this.years, Math.floor(nbYears));
    if (nbSeconds < 45) {
      time = this.seconds;
    } else if (nbSeconds < 90) {
      time = this.minute;
    } else if (nbMinutes < 45) {
      time = MessageFormat.format(this.minutes, Math.round(nbMinutes));
    } else if (nbMinutes < 90) {
      time = this.hour;
    } else if (nbHours < 24) {
      time = MessageFormat.format(this.hours, Math.round(nbHours));
    } else if (nbHours < 48) {
      time = this.day;
    } else if (nbDays < 30) {
      time = MessageFormat.format(this.days, Math.floor(nbDays));
    } else if (nbDays < 60) {
      time = this.month;
    } else if (nbDays < 365) {
      time = MessageFormat.format(this.months, Math.floor(nbDays / 30));
    } else if (nbYears < 2) {
      time = this.year;
    }

    return join(time, suffixAgo);
  }

  @VisibleForTesting
  String join(String time, String suffix) {
    StringBuilder joined = new StringBuilder();
    joined.append(time);
    if (StringUtils.isNotBlank(suffix)) {
      joined.append(' ').append(suffix);
    }
    return joined.toString();
  }

  public String getSuffixAgo() {
    return suffixAgo;
  }

  public String getSeconds() {
    return seconds;
  }

  public String getMinute() {
    return minute;
  }

  public String getMinutes() {
    return minutes;
  }

  public String getHour() {
    return hour;
  }

  public String getHours() {
    return hours;
  }

  public String getDay() {
    return day;
  }

  public String getDays() {
    return days;
  }

  public String getMonth() {
    return month;
  }

  public String getMonths() {
    return months;
  }

  public String getYear() {
    return year;
  }

  public String getYears() {
    return years;
  }

}
