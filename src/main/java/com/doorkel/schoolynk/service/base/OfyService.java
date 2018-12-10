package com.doorkel.schoolynk.service.base;

import com.doorkel.schoolynk.domain.Account;
import com.doorkel.schoolynk.domain.Action;
import com.doorkel.schoolynk.domain.ActionComment;
import com.doorkel.schoolynk.domain.BaseEntity;
import com.doorkel.schoolynk.domain.Contact;
import com.doorkel.schoolynk.domain.ContactItem;
import com.doorkel.schoolynk.domain.ContactView;
import com.doorkel.schoolynk.domain.Event;
import com.doorkel.schoolynk.domain.Form;
import com.doorkel.schoolynk.domain.FormAnswer;
import com.doorkel.schoolynk.domain.FormAnswerItem;
import com.doorkel.schoolynk.domain.FormItem;
import com.doorkel.schoolynk.domain.FormLabel;
import com.doorkel.schoolynk.domain.Geocode;
import com.doorkel.schoolynk.domain.Participant;
import com.doorkel.schoolynk.domain.UserAuth;
import com.doorkel.schoolynk.domain.UserDetail;
import com.doorkel.schoolynk.domain.UserInfo;
import com.doorkel.schoolynk.domain.Validation;
import com.doorkel.schoolynk.util.AppEngineMemcacheClientService;
import com.google.appengine.api.utils.SystemProperty;
import com.google.appengine.api.utils.SystemProperty.Environment.Value;
import com.google.cloud.datastore.DatastoreOptions;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.impl.translate.opt.joda.JodaTimeTranslators;
import org.springframework.stereotype.Service;

@Service
public class OfyService {

  static {
    if (SystemProperty.environment.value() == Value.Development
        && !DatastoreOptions.getDefaultInstance().getHost().contains("localhost")) {
      throw new RuntimeException("You must run local datastore emulator");
    }

    ObjectifyService.init(new ObjectifyFactory(new AppEngineMemcacheClientService()));

    JodaTimeTranslators.add(ObjectifyService.factory());
    factory().register(UserInfo.class);
    factory().register(Account.class);
    factory().register(UserDetail.class);
    factory().register(UserAuth.class);
    factory().register(Contact.class);
    factory().register(Action.class);
    factory().register(ActionComment.class);
    factory().register(ContactItem.class);
    factory().register(BaseEntity.class);
    factory().register(ContactView.class);
    factory().register(Event.class);
    factory().register(Form.class);
    factory().register(FormAnswer.class);
    factory().register(FormAnswerItem.class);
    factory().register(FormItem.class);
    factory().register(Participant.class);
    factory().register(Geocode.class);
    factory().register(Validation.class);
    factory().register(FormLabel.class);

  }

  public static Objectify ofy() {
    ObjectifyService.begin();
    return ObjectifyService.ofy();
  }

  public static ObjectifyFactory factory() {
    return ObjectifyService.factory();
  }
}

