/* ----------------------------------------------------------------------------
   The Kiwi Toolkit
   Copyright (C) 1998-2003 Mark A. Lindner

   This file is part of Kiwi.
   
   This library is free software; you can redistribute it and/or
   modify it under the terms of the GNU Library General Public
   License as published by the Free Software Foundation; either
   version 2 of the License, or (at your option) any later version.

   This library is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
   Library General Public License for more details.

   You should have received a copy of the GNU Library General Public
   License along with this library; if not, write to the Free
   Software Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 
   The author may be contacted at:
   
   mark_a_lindner@yahoo.com
   ----------------------------------------------------------------------------
   $Log: ChangeSupport.java,v $
   Revision 1.4  2003/01/19 09:34:08  markl
   Javadoc & comment header updates.

   Revision 1.3  2001/03/12 01:38:46  markl
   Source code cleanup.

   Revision 1.2  1999/01/10 03:26:20  markl
   added GPL header & RCS tag
   ----------------------------------------------------------------------------
*/

package kiwi.event;

import java.awt.event.*;
import javax.swing.event.*;

/** A support object for generating <code>ChangeEvent</code>s.
  *
  * @see javax.swing.event.ChangeEvent
  * @see javax.swing.event.ChangeListener
  *
  * @author Mark Lindner
  */

public class ChangeSupport
  {
  private EventListenerList listeners;
  private Object source;

  /** Construct a new <code>ChangeSupport</code> object.
    *
    * @param source The owner of this object (and the source of the events that
    * will be generated by it).
    */  
  
  public ChangeSupport(Object source)
    {
    this.source = source;
    listeners = new EventListenerList();
    }

  /** Add a <code>ChangeListener</code> to this object's list of listeners.
    *
    * @param listener The listener to add.
    */  
  
  public void addChangeListener(ChangeListener listener)
    {
    listeners.add(ChangeListener.class, listener);
    }

  /** Remove a <code>ChangeListener</code> from this object's list of
    * listeners.
    *
    * @param listener The listener to remove.
    */

  public void removeChangeListener(ChangeListener listener)
    {
    listeners.remove(ChangeListener.class, listener);
    }

  /** Fire a change event.
    */
  
  public void fireChangeEvent()
    {
    ChangeEvent evt = null;
    
    Object[] list = listeners.getListenerList();

    for(int i = list.length - 2; i >= 0; i -= 2)
      {
      if(list[i]==ChangeListener.class)
        {
        // Lazily create the event:
        if(evt == null)
          evt = new ChangeEvent(source);
        ((ChangeListener)list[i + 1]).stateChanged(evt);
        }
      }
    }
  
  }

/* end of source file */
