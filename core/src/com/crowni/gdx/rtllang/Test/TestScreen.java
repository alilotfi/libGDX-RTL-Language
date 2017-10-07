/*
 * *****************************************************************************
 * Copyright 2011 See AUTHORS file.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package com.crowni.gdx.rtllang.Test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.Align;
import com.crowni.gdx.rtllang.arabic.ArFont;
import com.crowni.gdx.rtllang.arabic.ArUtils;
import com.crowni.gdx.rtllang.utils.BaseScreen;

/**
 * Created by Crowni on 9/14/2017.
 **/
public class TestScreen extends BaseScreen {
    private static final String TAG = TestScreen.class.getSimpleName();

    private static final String ARABIC_LANGUAGE = "اللغة العربية";
    private static final String INSERT_YOUR_NAME = "أدخل إسمك";

    private ArFont arFont = new ArFont();

    @Override
    public void show() {
        super.show();

        Image image = new Image(new Texture("images/arab_wallpaper.png"));
        image.setFillParent(true);
        stage.addActor(image);

        /** FREE TYPE FONT 1 **/
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/jf_flat.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.characters += ArUtils.getAllChars().toString("");
        parameter.size = 48;
        parameter.color = Color.BROWN;
        parameter.borderColor = Color.GOLD;
        parameter.borderWidth = 1.5f;
        parameter.minFilter = Texture.TextureFilter.Linear;
        parameter.magFilter = Texture.TextureFilter.Linear;

        BitmapFont freeTypeFont = generator.generateFont(parameter);
        Label label = new Label(arFont.getRTLText(ARABIC_LANGUAGE), new Label.LabelStyle(freeTypeFont, Color.WHITE));
        label.setAlignment(Align.center);
        label.setPosition(256F, 400F, Align.right);
        stage.addActor(label);

        /** BITMAP FONT FONT 1 **/
//        BitmapFont bitmapFontJf_flat = new BitmapFont(Gdx.files.internal("fonts/jf_flat.fnt"));
//        label = new Label(arFont.getRTLText(ARABIC_LANGUAGE), new Label.LabelStyle(bitmapFontJf_flat, Color.WHITE));
//        label.setAlignment(Align.center);
//        label.setPosition(512F + 256F, 400F, Align.right);
//        stage.addActor(label);

        /** BITMAP FONT FONT 2 **/
        BitmapFont bitmapFontArabic = new BitmapFont(Gdx.files.internal("fonts/arabic.fnt"));
        TextArea.TextFieldStyle style = new TextField.TextFieldStyle();
        style.font = bitmapFontArabic;
        style.fontColor = Color.GOLD;
        TextField textField = new TextField(arFont.getRTLText(INSERT_YOUR_NAME), style);
        textField.setAlignment(Align.right);
        textField.setSize(BaseScreen.WIDTH, 100F);
        textField.setPosition(BaseScreen.WIDTH - 10F, BaseScreen.HEIGHT / 2 - 30F, Align.right);
        stage.addActor(textField);

        textField.setTextFieldListener(new TextField.TextFieldListener() {
            @Override
            public void keyTyped(TextField textField, char c) {
                String text = arFont.typing(c);
                textField.setText(text);
            }
        });
    }
}
