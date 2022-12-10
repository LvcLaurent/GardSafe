package fr.lsi.gardsafe.domain.common.event;

import java.util.List;

/**
 * Interface should be implemented by all aggregate roots that handle Domain events
 * 
 * @author Laurent SION
 *
 */
public interface EventAware {

  List<Event> getEvents();

  void cleanEvents();

}
