package com.orbit.mygame;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.ResolutionFileResolver;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.I18NBundle;

public class Assets {

    public AssetManager mngr;

    public Assets(){
        this.mngr = new AssetManager();

        //UI
        mngr.load("ui/default_uiskin.atlas", TextureAtlas.class);
        mngr.load("ui/default_uiskin.json", Skin.class);
        mngr.load("ui/settingsskin.atlas", TextureAtlas.class);
        mngr.load("ui/settingsskin.json", Skin.class);

        //Language
        mngr.load("languages/MyGame", I18NBundle.class);


        //Achievements atlas

        //Buildings atlas

        //Fonts
        FileHandleResolver resolver = new InternalFileHandleResolver();
        mngr.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(resolver));
        mngr.setLoader(BitmapFont.class, ".ttf", new FreetypeFontLoader(resolver));

        FreetypeFontLoader.FreeTypeFontLoaderParameter hdFont = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        hdFont.fontFileName = "fonts/AldotheApache.ttf";
        hdFont.fontParameters.size = 40;
        mngr.load("fonts/AldotheApache.ttf", BitmapFont.class, hdFont);
        //mngr.load("fonts/KazmannSans.ttf", BitmapFont.class, hdFont);

        //4K
        ResolutionFileResolver.Resolution res4K = new ResolutionFileResolver.Resolution(3840, 2160, "450p");
        //2K
        ResolutionFileResolver.Resolution res2K = new ResolutionFileResolver.Resolution(2560, 1040, "2160p");
        //HD
        ResolutionFileResolver.Resolution resHD2 = new ResolutionFileResolver.Resolution(1920, 1080, "1080p");
        //HD
        ResolutionFileResolver.Resolution resHD = new ResolutionFileResolver.Resolution(1280, 720, "1080p");
        //SD
        ResolutionFileResolver.Resolution resSD = new ResolutionFileResolver.Resolution(800, 450, "450p");
        FileHandleResolver resolutionFileResolver = new ResolutionFileResolver(new InternalFileHandleResolver(), res4K, res2K, resHD2, resHD, resSD );

        mngr.setLoader(Texture.class, new TextureLoader(resolutionFileResolver));
        mngr.load("bar.png", Texture.class);
    }

}
