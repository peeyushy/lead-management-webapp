package com.fhc.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;

import com.fhc.model.LeadNote;

public class StringToLeadNoteListConverter implements Converter<String, List<LeadNote>> {

	@Override
	public List<LeadNote> convert(String source) {
		if (source.isEmpty()) {
			return null;
		} else {
			List<LeadNote> LeadNotesLst = new ArrayList<>();
			LeadNote LeadNote = new LeadNote();
			LeadNote.setNote(source);
			LeadNotesLst.add(LeadNote);
			return LeadNotesLst;
		}
	}
}