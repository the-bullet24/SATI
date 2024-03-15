package CosapiSoft.SARAWebBanking;

/**
 * MaintainedServlet.java
 * Matt Tucker
 * CoolServlets.com
 * June 10, 1999
 * Version 1.0.2
 *
 *    Copyright (C) 1999  Matt Tucker
 *
 *    This library is free software; you can redistribute it and/or
 *    modify it under the terms of the GNU Library General Public
 *    License as published by the Free Software Foundation; either
 *    version 2 of the License, or (at your option) any later version.
 *
 *    This library is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *    Library General Public License for more details.
 *
 *    You should have received a copy of the GNU Library General Public
 *    License along with this library; if not, write to the
 *    Free Software Foundation, Inc., 59 Temple Place - Suite 330,
 *    Boston, MA  02111-1307, USA.
 */

/**
 * An interface that says that a java file is maintainable by a
 * ServletMaintenance thread. More information is available in the
 * ServletMaintenance class.
 *
 * @author Matt Tucker
 * @version 1.0.2
 */
public interface MaintainedServlet {
/**
   * The function that will be called by the ServletMaintenance object after
   * the specified time interval.
   */
public void doMaintenance();
}