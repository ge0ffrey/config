/*
 * JBoss, Home of Professional Open Source
 * Copyright 2010, Red Hat, Inc., and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.jboss.seam.xml.fieldset;

import java.util.List;
import java.util.Set;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.enterprise.inject.spi.InjectionTarget;

public class InjectionTargetWrapper<T> implements InjectionTarget<T>
{
   private final InjectionTarget<T> target;
   private final List<FieldValueObject> fieldValues;

   public InjectionTargetWrapper(InjectionTarget<T> target, List<FieldValueObject> fieldValues)
   {
      this.fieldValues = fieldValues;
      this.target = target;
   }

   public void inject(T instance, CreationalContext<T> ctx)
   {
      for (FieldValueObject f : fieldValues)
      {
         f.setValue(instance);
      }
      target.inject(instance, ctx);
   }

   public void postConstruct(T instance)
   {
      target.postConstruct(instance);
   }

   public void preDestroy(T instance)
   {
      target.preDestroy(instance);
   }

   public void dispose(T instance)
   {
      target.dispose(instance);
   }

   public Set<InjectionPoint> getInjectionPoints()
   {
      return target.getInjectionPoints();
   }

   public T produce(CreationalContext<T> ctx)
   {
      return target.produce(ctx);
   }

}
